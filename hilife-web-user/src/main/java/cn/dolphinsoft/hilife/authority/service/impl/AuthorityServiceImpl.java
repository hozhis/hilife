package cn.dolphinsoft.hilife.authority.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dolphinsoft.hilife.authority.service.LoginService;
import cn.dolphinsoft.hilife.common.authority.domain.FunctionInfo;
import cn.dolphinsoft.hilife.common.authority.domain.RoleInfo;
import cn.dolphinsoft.hilife.common.authority.domain.UserInfo;
import cn.dolphinsoft.hilife.common.authority.service.AuthorityService;
import cn.dolphinsoft.hilife.common.constant.BasicTypeConstant;
import cn.dolphinsoft.hilife.common.domain.BasicPara;
import cn.dolphinsoft.hilife.common.domain.CustUserInfo;
import cn.dolphinsoft.hilife.common.repository.ICustUserInfoRepository;
import cn.dolphinsoft.hilife.common.service.CacheService;

/**
 * Class Name: AuthorityServiceImpl Description: shiro认证实现类
 * 
 * @author hozhis
 *
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private LoginService loginService;

    @Autowired
    private ICustUserInfoRepository custUserInfoRepository;

    @Autowired
    private CacheService cacheService;

    @Override
    public UserInfo findUserInfoByToken(String token) {
        CustUserInfo userInfo = custUserInfoRepository.findByToken(token);
        return userInfo;
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {
        custUserInfoRepository.save((CustUserInfo) userInfo);
    }

    @Override
    public UserInfo authcUser(String loginId, String password) {
        String smsPwd = loginService.getLoginSms(loginId);
        if (smsPwd != null && smsPwd.equals(password)) {
            // 清除短信缓存
            loginService.clearLoginSms(loginId);
            // 从数据库查找用户，如果不存在就新建用户
            CustUserInfo userInfo = custUserInfoRepository.findByLoginId(loginId);
            if (userInfo == null) {
                userInfo = new CustUserInfo();
                userInfo.setLoginId(loginId);
                userInfo.setInviteCode(generateInviteCode(loginId));
            }
            return userInfo;
        }
        return null;
    }

    /** 用户端没有权限，以下方法无须查表，直接返回即可 **/

    @Override
    public List<RoleInfo> findRoleInfosByToken(String token) {
        return null;
    }

    @Override
    public List<FunctionInfo> findFunctionsByRoleIds(Iterable<Integer> roleIds) {
        return null;
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
        String[] path = new String[2];
        String staticPath = "hilife.dolphinsoft.cn";
        List<BasicPara> basicParas = cacheService.findBasicParaByTypeId(BasicTypeConstant.FTP_SETTING_PLATFORM);
        if (basicParas != null && !basicParas.isEmpty()) {
            staticPath = basicParas.get(0).getParaValue1();
        }
        path[1] = staticPath;
        return path;
    }

    @Override
    public Map<Integer, Integer> getMenus() {
        return null;
    }

    @Override
    public Map<String, Integer> getPages() {
        return null;
    }

    private String generateInviteCode(String loginId) {
        String base = "abcdefghjklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        String inviteCode = sb.toString().toUpperCase();
        CustUserInfo custUserInfo = custUserInfoRepository.findByInviteCode(inviteCode);
        if (custUserInfo != null) {
            generateInviteCode(loginId);
        }
        return inviteCode;
    }

}
