package cn.dolphinsoft.hilife.authority.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dolphinsoft.hilife.authority.constant.Consts;
import cn.dolphinsoft.hilife.authority.constant.TreeNodeBean;
import cn.dolphinsoft.hilife.authority.dto.PlatformRoleInfoDto;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformRoleInfoSaveAndUpdateDto;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformRoleInfoSearchDto;
import cn.dolphinsoft.hilife.authority.service.PlatformUserRoleService;
import cn.dolphinsoft.hilife.common.domain.PlatformFunctionInfo;
import cn.dolphinsoft.hilife.common.domain.PlatformRoleFunction;
import cn.dolphinsoft.hilife.common.domain.PlatformRoleInfo;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.enumeration.BaseStatus;
import cn.dolphinsoft.hilife.common.enumeration.UserInfoStatusEnum;
import cn.dolphinsoft.hilife.common.exception.HiLifeException;
import cn.dolphinsoft.hilife.common.repository.PlatformFunctionInfoRepository;
import cn.dolphinsoft.hilife.common.repository.PlatformRoleFunctionInfoRepository;
import cn.dolphinsoft.hilife.common.repository.PlatformRoleInfoRepository;
import cn.dolphinsoft.hilife.common.util.HiLifeUtil;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PlatformUserRoleServiceImpl implements PlatformUserRoleService {

    @Autowired
    private PlatformRoleInfoRepository platformRoleInfoRepository;

    @Autowired
    private PlatformFunctionInfoRepository platformFunctionInfoRepository;

    @Autowired
    private PlatformRoleFunctionInfoRepository platformRoleFunctionInfoRepository;

    private final Integer ROLE_INFO_ID = 1;

    @Override
    public void search(final PlatformRoleInfoSearchDto dto) {

        PageRequest pageable = new PageRequest(dto.getCurrentPage() - 1, dto.getPageSize(),
                new Sort(Direction.DESC, "createDate"));

        Page<PlatformRoleInfo> page = platformRoleInfoRepository.findAll(new Specification<PlatformRoleInfo>() {
            @Override
            public Predicate toPredicate(Root<PlatformRoleInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (!HiLifeUtil.isEmpty(dto.getMsg())) {
                    String msg = "";
                    if (!dto.getMsg().contains("%")) {
                        msg = "%" + dto.getMsg().trim().toLowerCase() + "%";
                    }
                    Predicate p1 = cb.like(cb.lower(root.<String> get("role")), msg);
                    predicates.add(p1);
                }
                Predicate p2 = cb.equal(root.<String> get("status"), BaseStatus.EFFECT.getKey());
                predicates.add(p2);
                Predicate predicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
                query.where(predicate);
                return query.getRestriction();
            }
        }, pageable);
        dto.setTotalRecord(page.getTotalElements());
        dto.setTotalPages(page.getTotalPages());
        dto.setList(buildStoreDetailDtos(page.getContent()));
    }

    private List<PlatformRoleInfoDto> buildStoreDetailDtos(List<PlatformRoleInfo> list) {
        List<PlatformRoleInfoDto> dto = new ArrayList<PlatformRoleInfoDto>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (PlatformRoleInfo r : list) {
            PlatformRoleInfoDto udto = new PlatformRoleInfoDto();
            udto.setRole(r.getRole());
            udto.setRoleId(r.getRoleId());
            udto.setCreateDate(dateFormat.format(r.getCreateDate()));
            udto.setStatus(UserInfoStatusEnum.getValueByKey(r.getStatus()));
            dto.add(udto);
        }
        return dto;
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public ResultDto<String> saveAndupdate(PlatformRoleInfoSaveAndUpdateDto dto) {
        ResultDto<String> msg;
        if (dto.getRoleId() == null) {
            msg = save(dto);
        } else {
            msg = update(dto);
        }
        return msg;
    }

    public ResultDto<String> save(PlatformRoleInfoSaveAndUpdateDto dto) {
        PlatformRoleInfo entity = new PlatformRoleInfo();
        if (HiLifeUtil.isEmpty(dto.getRole())) {
            return ResultDtoFactory.toNack("请输入角色名！");
        }
        if (dto.getRole().length() > 6) {
            return ResultDtoFactory.toNack("角色名称过长，请输入不多于6个字的名称！");
        }
        String str = platformRoleInfoRepository.findByRoleAndStatus(dto.getRole(), BaseStatus.EFFECT.getKey());
        if (!HiLifeUtil.isEmpty(str)) {// 不为空，说明数据表中有这个角色名
            return ResultDtoFactory.toNack("角色名称已存在，请重新输入！");
        }
        List<Integer> functionIds = dto.getFunctionIds();
        if (functionIds.isEmpty()) {
            return ResultDtoFactory.toNack("请至少选择一个权限！");
        }
        entity.setRole(dto.getRole());
        if (!HiLifeUtil.isEmpty(dto.getDescription())) {
            if (dto.getDescription().length() > 20) {
                return ResultDtoFactory.toNack("描述过长，请输入不多于20个字的名称！");
            }
            entity.setDescription(dto.getDescription());
        }

        entity.setCreateDate(new Date());
        entity.setStatus(BaseStatus.EFFECT.getKey());
        // 保存角色
        platformRoleInfoRepository.save(entity);

        // 从functionIds中分离出不是左侧菜单栏的值
        List<Integer> listChilds = new ArrayList<Integer>();

        List<PlatformFunctionInfo> parents = platformFunctionInfoRepository.findByFunctionIdIn(functionIds);
        // 获取functionIds中父节点的Id
        List<PlatformRoleFunction> resultList = new ArrayList<PlatformRoleFunction>();
        for (PlatformFunctionInfo info : parents) {
            if (info.getParentId() == 0) {
                PlatformRoleFunction platformRoleFunction = new PlatformRoleFunction();
                platformRoleFunction.setFunctionId(info.getFunctionId());
                platformRoleFunction.setRoleId(entity.getRoleId());
                resultList.add(platformRoleFunction);
            } else {
                listChilds.add(info.getFunctionId());
            }
        }
        if (resultList != null || resultList.size() > 0) {
            platformRoleFunctionInfoRepository.save(resultList);
        }

        // 获取子节点
        for (Integer functionId : listChilds) {
            List<Integer> childs = platformFunctionInfoRepository.findByLevelId("," + functionId + ",", functionId);
            List<PlatformRoleFunction> resultChilds = new ArrayList<PlatformRoleFunction>();
            for (Integer id : childs) {
                PlatformRoleFunction platformRoleFunction = new PlatformRoleFunction();
                platformRoleFunction.setFunctionId(id);
                platformRoleFunction.setRoleId(entity.getRoleId());
                resultChilds.add(platformRoleFunction);
            }
            platformRoleFunctionInfoRepository.save(resultChilds);
        }

        return ResultDtoFactory.toAck("保存成功！");
    }

    @Override
    public List<TreeNodeBean> getFunctionTree(String functionId) {
        // 默认根节点为0
        if (HiLifeUtil.isEmpty(functionId)) {
            functionId = Consts.TREE_ROOT_ID;
        }
        List<PlatformFunctionInfo> functionList = platformFunctionInfoRepository
                .findFunctionTreeList("," + functionId + ",");
        List<TreeNodeBean> treeList = new ArrayList<TreeNodeBean>();
        Map<Integer, TreeNodeBean> nodeMap = new HashMap<Integer, TreeNodeBean>();
        for (int i = functionList.size() - 1; i >= 0; i--) {
            PlatformFunctionInfo functionInfo = functionList.get(i);
            TreeNodeBean nodeBean = new TreeNodeBean();
            nodeBean.setId(functionInfo.getFunctionId());
            nodeBean.setPid(functionInfo.getParentId());
            nodeBean.setNodeName(functionInfo.getFunctionName());
            nodeBean.setLevel(functionInfo.getLevel());
            nodeMap.put(nodeBean.getId(), nodeBean);
            if (nodeMap.containsKey(functionInfo.getParentId())) {
                nodeMap.get(functionInfo.getParentId()).getChildrenList().add(nodeBean);
            } else {
                treeList.add(nodeBean);
            }
        }
        return treeList;
    }

    @Override
    public PlatformRoleInfoSaveAndUpdateDto findPlatformById(Integer roleId) {
        PlatformRoleInfo info = platformRoleInfoRepository.findOne(roleId);

        List<PlatformRoleFunction> list = platformRoleFunctionInfoRepository.findAll(roleId);
        List<Integer> functionIds = new ArrayList<Integer>();
        for (PlatformRoleFunction pfrf : list) {
            functionIds.add(pfrf.getFunctionId());
        }
        PlatformRoleInfoSaveAndUpdateDto dto = new PlatformRoleInfoSaveAndUpdateDto();
        dto.setFunctionIds(functionIds);
        dto.setRoleId(info.getRoleId());
        dto.setDescription(info.getDescription());
        dto.setRole(info.getRole());
        return dto;
    }

    public ResultDto<String> update(PlatformRoleInfoSaveAndUpdateDto dto) {
        PlatformRoleInfo info = platformRoleInfoRepository.findOne(dto.getRoleId());
        if (!HiLifeUtil.isEmpty(dto.getRole())) {
            if (!dto.getRole().equals(info.getRole())) {
                String str = platformRoleInfoRepository.findByRoleAndStatus(dto.getRole(), BaseStatus.EFFECT.getKey());
                if (!HiLifeUtil.isEmpty(str)) {// 不为空，说明数据表中有这个角色名
                    return ResultDtoFactory.toNack("角色名称已存在，请重新输入！");
                }
                if (dto.getRole().length() > 6) {
                    return ResultDtoFactory.toNack("角色名称过长，请输入不多于6个字的名称！");
                }
            }
            info.setRole(dto.getRole());
        }
        if (!HiLifeUtil.isEmpty(dto.getDescription())) {
            info.setDescription(dto.getDescription());
        }
        List<Integer> functionIds = dto.getFunctionIds();
        if (functionIds.isEmpty()) {
            return ResultDtoFactory.toNack("请至少选择一个权限！");
        }
        platformRoleInfoRepository.save(info);

        platformRoleFunctionInfoRepository.deleteByRoleId(dto.getRoleId());

        // 从functionIds中分离出不是左侧菜单栏的值
        List<Integer> listChilds = new ArrayList<Integer>();

        List<PlatformFunctionInfo> parents = platformFunctionInfoRepository.findByFunctionIdIn(functionIds);
        // 获取functionIds中父节点的Id
        List<PlatformRoleFunction> resultList = new ArrayList<PlatformRoleFunction>();
        for (PlatformFunctionInfo functionInfo : parents) {
            if (functionInfo.getParentId() == 0) {
                PlatformRoleFunction platformRoleFunction = new PlatformRoleFunction();
                platformRoleFunction.setFunctionId(functionInfo.getFunctionId());
                platformRoleFunction.setRoleId(info.getRoleId());
                resultList.add(platformRoleFunction);
            } else {
                listChilds.add(functionInfo.getFunctionId());
            }
        }
        if (resultList != null || resultList.size() > 0) {
            platformRoleFunctionInfoRepository.save(resultList);
        }

        // 获取子节点
        for (Integer functionId : listChilds) {
            List<Integer> childs = platformFunctionInfoRepository.findByLevelId("," + functionId + ",", functionId);
            List<PlatformRoleFunction> resultChilds = new ArrayList<PlatformRoleFunction>();
            for (Integer id : childs) {
                PlatformRoleFunction platformRoleFunction = new PlatformRoleFunction();
                platformRoleFunction.setFunctionId(id);
                platformRoleFunction.setRoleId(info.getRoleId());
                resultChilds.add(platformRoleFunction);
            }
            platformRoleFunctionInfoRepository.save(resultChilds);
        }
        return ResultDtoFactory.toAck("更新成功！");
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public void logout(Integer roleId) {
        if (ROLE_INFO_ID.equals(roleId)) {
            throw new HiLifeException("该角色无法操作注销，请选择其他角色！");
        }
        List<PlatformRoleInfo> list = platformRoleInfoRepository.findByRoleId(roleId);
        if (list.size() > 0) {
            throw new HiLifeException("该角色已绑定账户，无法操作注销，请选择其他角色！");
        }
        platformRoleInfoRepository.logout(roleId, BaseStatus.INVALID.getKey());
    }

}
