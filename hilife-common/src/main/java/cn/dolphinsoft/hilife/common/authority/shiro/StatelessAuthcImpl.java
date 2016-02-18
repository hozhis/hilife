package cn.dolphinsoft.hilife.common.authority.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.authority.domain.FunctionInfo;
import cn.dolphinsoft.hilife.common.authority.domain.RoleInfo;
import cn.dolphinsoft.hilife.common.authority.domain.UserInfo;
import cn.dolphinsoft.hilife.common.authority.service.AuthorityService;
import cn.dolphinsoft.hilife.common.memcached.constant.CacheType;

/**
 * Class Name: StatelessAuthImpl Description: 权限认证实现
 * 
 * @author hozhis
 *
 */
@Component
public class StatelessAuthcImpl {

    @Autowired
    private AuthorityService authorityService;

    private final Random random = new Random(System.currentTimeMillis());

    @Cacheable(value = CacheType.DEFAULT, key = "#principals.getPrimaryPrincipal().token + '_SimpleAuthorizationInfo'")
    public SimpleAuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        // 获得该用户的全部角色
        List<RoleInfo> roleInfos = authorityService.findRoleInfosByToken(userInfo.getToken());
        if (roleInfos != null) {
            Set<String> roles = new HashSet<String>();
            Set<Integer> roleIds = new HashSet<Integer>();
            for (RoleInfo roleInfo : roleInfos) {
                roles.add(roleInfo.getRole());
                roleIds.add(roleInfo.getRoleId());
            }
            // 获得该用户的全部权限
            List<FunctionInfo> functionInfos = authorityService.findFunctionsByRoleIds(roleIds);
            Set<String> permissions = new HashSet<String>();
            if (functionInfos != null) {
                for (FunctionInfo functionInfo : functionInfos) {
                    permissions.add(functionInfo.getPermission());
                }
            }
            authorizationInfo.addRoles(roles);
            authorizationInfo.addStringPermissions(permissions);
        }
        return authorizationInfo;
    }

    @Cacheable(value = CacheType.DEFAULT, condition = "#token instanceof T(com.zcckj.common.authority.shiro.StatelessToken)", key = "#token.getPrincipal() + '_SimpleAuthenticationInfo'")
    public SimpleAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        String tokenStr = null;
        UserInfo userInfo = null;
        SimpleAuthenticationInfo authenticationInfo = null;
        if (token instanceof StatelessToken) {
            tokenStr = (String) token.getPrincipal();
            userInfo = authorityService.findUserInfoByToken(tokenStr);
            if (userInfo == null) {
                throw new ExpiredCredentialsException("Token[" + tokenStr + "]不正确或已失效");
            }
        } else if (token instanceof UsernamePasswordToken) {
            String loginId = ((UsernamePasswordToken) token).getUsername();
            String password = String.valueOf(((UsernamePasswordToken) token).getPassword());
            userInfo = authorityService.authcUser(loginId, password);
            if (userInfo != null) {
                // 清除该用户当前的Token缓存
                AuthorityContext.clearUserInfoCache(userInfo.getToken());
                AuthorityContext.clearAuthzCache(userInfo.getToken());
                AuthorityContext.clearAuthcCache(userInfo.getToken());
                // 设置新的token
                tokenStr = getRandomToken();
                userInfo.setToken(tokenStr);
                authorityService.saveUserInfo(userInfo);
            } else {
                throw new IncorrectCredentialsException("用户[" + loginId + "]认证失败");
            }
        }
        if (userInfo != null) {
            authenticationInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword(), userInfo.getLoginId());
        }
        return authenticationInfo;
    }

    /**
     * Description: 获得一个随机数作为Token
     *
     * @return
     */
    private String getRandomToken() {
        return Long.toString(System.currentTimeMillis(), 31) + Long.toString(System.nanoTime(), 19)
                + Long.toString(random.nextLong(), 23);
    }

}
