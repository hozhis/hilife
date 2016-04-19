package cn.dolphinsoft.hilife.authority.service.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import cn.dolphinsoft.hilife.common.enumeration.UserInfoStatusEnum;
import cn.dolphinsoft.hilife.authority.dto.PlatformUserInfoDto;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformUserInfoDetailDto;
import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformUserInfoSearchDto;
import cn.dolphinsoft.hilife.authority.service.UserManagerService;
import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.constant.BasicTypeConstant;
import cn.dolphinsoft.hilife.common.domain.PlatformRoleInfo;
import cn.dolphinsoft.hilife.common.domain.PlatformUserInfo;
import cn.dolphinsoft.hilife.common.domain.PlatformUserRole;
import cn.dolphinsoft.hilife.common.domain.SmsMessage;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.enumeration.BaseStatus;
import cn.dolphinsoft.hilife.common.exception.HiLifeException;
import cn.dolphinsoft.hilife.common.repository.BasicParaRepository;
import cn.dolphinsoft.hilife.common.repository.PlatformRoleInfoRepository;
import cn.dolphinsoft.hilife.common.repository.PlatformUserInfoRepository;
import cn.dolphinsoft.hilife.common.repository.PlatformUserRoleRepository;
import cn.dolphinsoft.hilife.common.repository.SmsMessageRepository;
import cn.dolphinsoft.hilife.common.util.EncryptUtil;
import cn.dolphinsoft.hilife.common.util.HiLifeUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Class Name: UserManagerServiceImpl Description:
 * 
 * @author hozhis
 *
 */
@Service
public class UserManagerServiceImpl implements UserManagerService {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(UserManagerServiceImpl.class);

    @Autowired
    private PlatformUserInfoRepository platformUserInfoRepository;

    @Autowired
    private PlatformRoleInfoRepository platformRoleInfoRepository;

    @Autowired
    private PlatformUserRoleRepository platformUserRoleRepository;

    @Resource(name = "freemarkerConfigurer")
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private BasicParaRepository basicParaRepository;

    @Autowired
    private SmsMessageRepository smsMessageRepository;

    @Override
    public void search(final PlatformUserInfoSearchDto dto) {
        PageRequest pageable = new PageRequest(dto.getCurrentPage() - 1, dto.getPageSize(),
                new Sort(Direction.ASC, "userId"));

        Page<PlatformUserInfo> page = platformUserInfoRepository.findAll(new Specification<PlatformUserInfo>() {
            @Override
            public Predicate toPredicate(Root<PlatformUserInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if (!HiLifeUtil.isEmpty(dto.getMsg())) {
                    String msg = "";
                    if (!dto.getMsg().contains("%"))
                        msg = "%" + dto.getMsg().trim().toLowerCase() + "%";
                    Predicate p1 = cb.like(cb.lower(root.<String> get("userName")), msg);
                    Predicate p2 = cb.like(cb.lower(root.<String> get("loginId")), msg);
                    predicates.add(cb.or(p1, p2));
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

    private List<PlatformUserInfoDto> buildStoreDetailDtos(List<PlatformUserInfo> list) {
        List<PlatformUserInfoDto> dto = new ArrayList<PlatformUserInfoDto>();
        for (PlatformUserInfo r : list) {
            PlatformUserInfoDto info = new PlatformUserInfoDto();
            info.setLoginId(r.getLoginId());
            info.setUserId(r.getUserId());
            info.setUserName(r.getUserName());
            info.setEmail(r.getEmail());
            info.setPhone(r.getPhone());
            info.setStatus(UserInfoStatusEnum.getValueByKey(r.getStatus()));
            dto.add(info);
        }

        return dto;
    }

    @Override
    public List<PlatformRoleInfo> findPlatformRoleInfo() {
        return platformRoleInfoRepository.findAllByStatus(BaseStatus.EFFECT.getKey());
    }

    public ResultDto<String> save(PlatformUserInfoDetailDto dto) {
        PlatformUserInfo info = new PlatformUserInfo();
        if (!HiLifeUtil.isEmpty(dto.getLoginId())) {
            PlatformUserInfo sinfo1 = platformUserInfoRepository.findByLoginId(dto.getLoginId(),
                    BaseStatus.EFFECT.getKey());
            if (sinfo1 != null) {
                return ResultDtoFactory.toNack("登录用户名已经存在，请重新输入！");
            }
            if (dto.getLoginId().length() > 13) {
                return ResultDtoFactory.toNack("登录用户名过长，请重新输入！！");
            }
            info.setLoginId(dto.getLoginId());
        } else {
            return ResultDtoFactory.toNack("账户不能为空，请重新输入!");
        }

        if (!HiLifeUtil.isEmpty(String.valueOf(dto.getPhone()))) {
            PlatformUserInfo sinfo = platformUserInfoRepository.findByPhone(dto.getPhone(), BaseStatus.EFFECT.getKey());
            if (sinfo != null) {
                return ResultDtoFactory.toNack("该手机号码已存在，请重新输入!");
            }
            info.setPhone(dto.getPhone());
        } else {
            return ResultDtoFactory.toNack("手机号码不能为空，请重新输入！");
        }

        if (!HiLifeUtil.isEmpty(dto.getUserName())) {
            info.setUserName(dto.getUserName());
        } else {
            return ResultDtoFactory.toNack("账户用户名不能为空，请重新输入！");
        }
        if (!HiLifeUtil.isEmpty(dto.getRemark())) {
            info.setRemark(dto.getRemark());
        }

        if (!HiLifeUtil.isEmpty(dto.getEmail())) {
            info.setEmail(dto.getEmail());
        }

        if (HiLifeUtil.isEmpty(dto.getRoles())) {
            return ResultDtoFactory.toNack("请至少选择一个角色！");
        }

        // Random random = new Random();
        // 随机生成6位数密码
        // int password = random.nextInt(899999)+100000;
        int password = 123456;
        // 对密码保存到短信模板中
        String smsDx = basicParaRepository.findParaValue1ByTypeId(BasicTypeConstant.PLATFORM_SMS_TEMPLATE_GET_CAPTCHA);
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name_platform", dto.getLoginId());
        dataMap.put("password", password);
        StringWriter out = new StringWriter();
        try {
            new Template("template", new StringReader(smsDx), configuration).process(dataMap, out);
            smsDx = out.toString();
        } catch (Exception e) {
            logger.info("模板解析失败！");
            e.printStackTrace();
        }
        SmsMessage smess = new SmsMessage();
        smess.setPhone(dto.getPhone());
        smess.setCreateDate(new Date());
        smess.setStatus(BaseStatus.EFFECT.getKey());
        smess.setMessage(smsDx);
        smsMessageRepository.save(smess);

        // 对密码进行2次加密
        String password1 = EncryptUtil.encryptMd5(String.valueOf(password));
        String password2 = EncryptUtil.encryptMd5(password1, dto.getLoginId());
        info.setPassword(password2);
        info.setStatus(BaseStatus.EFFECT.getKey());
        info.setToken("");
        platformUserInfoRepository.save(info);

        String[] roles = dto.getRoles().split(",");
        List<PlatformUserRole> list = new ArrayList<PlatformUserRole>(roles.length);
        for (String roleId : roles) {
            PlatformUserRole userRole = new PlatformUserRole();
            userRole.setRoleId(Integer.parseInt(roleId));
            userRole.setUserId(info.getUserId());
            list.add(userRole);
        }
        platformUserRoleRepository.save(list);

        return ResultDtoFactory.toAck("注册成功!");
    }

    @Override
    public PlatformUserInfoDetailDto findPlatformUserInfo(Integer userId) {
        PlatformUserInfo info = platformUserInfoRepository.findOne(userId);
        PlatformUserInfoDetailDto dto = new PlatformUserInfoDetailDto();
        dto.setLoginId(info.getLoginId());
        dto.setUserName(info.getUserName());
        dto.setUserId(info.getUserId());
        dto.setOldPassword(info.getPassword());
        dto.setEmail(info.getEmail());
        dto.setPhone(info.getPhone());
        dto.setRemark(info.getRemark());

        // 通过关联表将信息查询出来
        List<PlatformRoleInfo> list = platformRoleInfoRepository.findByUserId(userId);
        String roles = "";
        for (PlatformRoleInfo roleInfo : list) {
            roles = roles + roleInfo.getRoleId() + ",";
        }
        dto.setRoles(roles);
        return dto;
    }

    public ResultDto<String> update(PlatformUserInfoDetailDto dto) {
        PlatformUserInfo info = platformUserInfoRepository.findOne(dto.getUserId());
        if (!HiLifeUtil.isEmpty(dto.getLoginId())) {
            if (!dto.getLoginId().equals(info.getLoginId())) {
                PlatformUserInfo info1 = platformUserInfoRepository.findByLoginId(dto.getLoginId(),
                        BaseStatus.EFFECT.getKey());
                if (info1 != null)
                    return ResultDtoFactory.toNack("该登录账户名已经存在，请重新输入！");
            }
            if (dto.getLoginId().length() > 13)
                return ResultDtoFactory.toNack("登录用户名过长，请重新输入！！");
            info.setLoginId(dto.getLoginId());
        } else {
            return ResultDtoFactory.toNack("登录账户名不能为空，请重新输入!");
        }

        if (!HiLifeUtil.isEmpty(String.valueOf(dto.getPhone()))) {
            if (!dto.getPhone().equals(info.getPhone())) {
                PlatformUserInfo sinfo = platformUserInfoRepository.findByPhone(dto.getPhone(),
                        BaseStatus.EFFECT.getKey());
                if (sinfo != null)
                    return ResultDtoFactory.toNack("该手机号码已存在，请重新输入!");
            }
            info.setPhone(dto.getPhone());
        } else {
            return ResultDtoFactory.toNack("手机号码不能为空，请重新输入！");
        }
        if (HiLifeUtil.isEmpty(dto.getRoles()))
            return ResultDtoFactory.toNack("请至少选择一个角色！");
        if (!HiLifeUtil.isEmpty(dto.getUserName()))
            info.setUserName(dto.getUserName());
        if (!HiLifeUtil.isEmpty(dto.getRemark()))
            info.setRemark(dto.getRemark());
        if (!HiLifeUtil.isEmpty(dto.getEmail()))
            info.setEmail(dto.getEmail());
        platformUserInfoRepository.save(info);

        // 更新关联表
        String[] roles = dto.getRoles().split(",");
        List<PlatformUserRole> listUserRole = new ArrayList<PlatformUserRole>();
        for (String roleId : roles) {
            PlatformUserRole userRole = new PlatformUserRole();
            userRole.setRoleId(Integer.parseInt(roleId));
            userRole.setUserId(info.getUserId());
            listUserRole.add(userRole);
        }
        platformUserRoleRepository.deleteByUserId(info.getUserId());
        platformUserRoleRepository.save(listUserRole);
        return ResultDtoFactory.toAck("更新成功!");
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public void logout(Integer userId) {
        // 判断是否是当前账户，如果是，则不能注销
        if (userId.equals(AuthorityContext.getCurrentUser().getUserId())) {
            throw new HiLifeException("该账户为当前登录账户，无法注销！");
        }
        platformUserInfoRepository.logout(userId, BaseStatus.INVALID.getKey());
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public ResultDto<String> saveAndupdate(PlatformUserInfoDetailDto dto) {
        ResultDto<String> msg = null;
        if (dto.getUserId() == null) {
            msg = save(dto);
        } else {
            msg = update(dto);
        }
        return msg;
    }

}
