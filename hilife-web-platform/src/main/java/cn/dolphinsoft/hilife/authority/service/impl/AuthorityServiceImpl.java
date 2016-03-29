package cn.dolphinsoft.hilife.authority.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dolphinsoft.hilife.common.authority.domain.FunctionInfo;
import cn.dolphinsoft.hilife.common.authority.domain.RoleInfo;
import cn.dolphinsoft.hilife.common.authority.domain.UserInfo;
import cn.dolphinsoft.hilife.common.authority.service.AuthorityService;
import cn.dolphinsoft.hilife.common.constant.BasicTypeConstant;
import cn.dolphinsoft.hilife.common.domain.PlatformFunctionInfo;
import cn.dolphinsoft.hilife.common.domain.PlatformUserInfo;
import cn.dolphinsoft.hilife.common.enumeration.BaseStatus;
import cn.dolphinsoft.hilife.common.repository.BasicParaRepository;
import cn.dolphinsoft.hilife.common.repository.PlatformFunctionInfoRepository;
import cn.dolphinsoft.hilife.common.repository.PlatformRoleInfoRepository;
import cn.dolphinsoft.hilife.common.repository.PlatformUserInfoRepository;
import cn.dolphinsoft.hilife.common.util.EncryptUtil;

/**
 * Class Name: AuthorityServiceImpl Description: shiro认证实现类
 * 
 * @author hozhis
 *
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private PlatformUserInfoRepository userInfoRepository;

    @Autowired
    private PlatformRoleInfoRepository roleInfoRepository;

    @Autowired
    private PlatformFunctionInfoRepository functionInfoRepository;

    @Autowired
    private BasicParaRepository basicParaRepository;

    private final String TYPE_LEFT = "0";

    private final String TYPE_PAGE = "1";

    @Override
    public UserInfo findUserInfoByToken(String token) {
        List<PlatformUserInfo> userInfos = userInfoRepository.findByToken(token);
        if (!userInfos.isEmpty()) {
            return userInfos.get(0);
        }
        return null;
    }

    @Override
    public UserInfo authcUser(String loginId, String password) {
        String encryptPwd = EncryptUtil.encryptMd5(password, loginId);
        List<PlatformUserInfo> userInfos = userInfoRepository.findByLoginIdAndPassword(loginId, encryptPwd,
                BaseStatus.EFFECT.getKey());
        if (!userInfos.isEmpty()) {
            return userInfos.get(0);
        }
        return null;
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        userInfoRepository.save((PlatformUserInfo) userInfo);
    }

    @Override
    public List<RoleInfo> findRoleInfosByToken(String token) {
        List<RoleInfo> infos = new ArrayList<RoleInfo>();
        infos.addAll(roleInfoRepository.findRoleInfoByToken(token, BaseStatus.EFFECT.getKey()));
        return infos;
    }

    @Override
    public List<FunctionInfo> findFunctionsByRoleIds(Iterable<Integer> roleIds) {
        List<FunctionInfo> infos = new ArrayList<FunctionInfo>();
        infos.addAll(functionInfoRepository.findFunctionByRoleIds(roleIds));
        return infos;
    }

    @Override
    public void clearUserInfoCache(String token) {
    }

    @Override
    public void clearAuthcCache(String token) {
    }

    @Override
    public void clearAuthzCache(String token) {
    }

    @Override
    public String[] getStaticPath() {
        String[] str = new String[2];
        str[0] = basicParaRepository.findParaValue1ByTypeId(BasicTypeConstant.PLATFORM_SETTING_PLATFORM);
        str[1] = basicParaRepository.findParaValue1ByTypeId(BasicTypeConstant.FTP_SETTING_PLATFORM);
        return str;
    }

    @Override
    public Map<Integer, Integer> getMenus() {
        List<PlatformFunctionInfo> list = functionInfoRepository.findByType(TYPE_LEFT);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        if (!list.isEmpty()) {
            for (PlatformFunctionInfo platformFunctionInfo : list) {
                map.put(platformFunctionInfo.getFunctionId(), platformFunctionInfo.getPriority());
            }
        }
        return map;
    }

    @Override
    public Map<String, Integer> getPages() {
        List<PlatformFunctionInfo> list = functionInfoRepository.findByType(TYPE_PAGE);
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (!list.isEmpty()) {
            for (PlatformFunctionInfo platformFunctionInfo : list) {
                String[] parents = platformFunctionInfo.getParentIds().split(",");
                map.put(platformFunctionInfo.getUrl(), Integer.parseInt(parents[2]));
            }
        }
        return map;
    }

}
