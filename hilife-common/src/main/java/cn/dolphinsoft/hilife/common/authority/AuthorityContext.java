/*
 * Project Name: hilife-common
 * File Name: AuthorityContext.java
 * Class Name: AuthorityContext
 *
 * Copyright 2016 dolphinsoft.cn
 *
 * Licensed under the author zhishenghong
 *
 * http://www.dolphinsoft.cn
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.dolphinsoft.hilife.common.authority;

import java.util.Collection;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.dolphinsoft.hilife.common.authority.domain.UserInfo;
import cn.dolphinsoft.hilife.common.authority.service.AuthorityService;
import cn.dolphinsoft.hilife.common.util.ApplicationContextUtil;

/**
 * Class Name: AuthorityContext Description: 权限容器
 * 
 * @author hozhis
 *
 */
public class AuthorityContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityContext.class);

    private final static ThreadLocal<UserInfo> UESR_INFO = new ThreadLocal<>();

    /**
     * Description: 获取当前用户的基本信息。
     * 
     * @return
     */
    public static UserInfo getCurrentUser() {
        return UESR_INFO.get();
    }

    /**
     * Description: 设置当前用户的基本信息。
     * 
     * @return
     */
    public static void setCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
        if (userInfo != null) {
            UESR_INFO.set(userInfo);
        }
    }

    /**
     * Description: 获得当前用户Token
     *
     * @return
     */
    public static String getCurrentToken() {
        Subject currentUser = SecurityUtils.getSubject();
        UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
        if (userInfo != null) {
            return userInfo.getToken();
        }
        return null;
    }

    /**
     * Description: 登录验证该用户名, 登陆登陆成功后会创建新的会话。
     * 
     * @param userId
     * @param password
     * 
     */
    public static String login(String userName, String password)
            throws IncorrectCredentialsException, LockedAccountException {
        long start = System.currentTimeMillis();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
        UserInfo userInfo = (UserInfo) currentUser.getPrincipal();
        String tokenStr = null;
        if (userInfo != null) {
            tokenStr = userInfo.getToken();
        }
        LOGGER.debug("User {} login successfully, token {}", userName, tokenStr);
        long end = System.currentTimeMillis();
        LOGGER.debug("login() completed for user {}, total time spent: {}ms", userName, end - start);
        return tokenStr;
    }

    /**
     * Description: 验证当前用户是否拥有该权限。
     *
     * @param permission
     * @return
     */
    public static boolean hasPermission(String permission) {
        Subject subject = SecurityUtils.getSubject();
        return subject == null ? false : subject.isPermitted(permission);
    }

    /**
     * Description: 验证当前用户是否拥有所有以下权限。
     *
     * @param permissions
     * @return
     */
    public static boolean hasAllPermissions(String... permissions) {
        Subject subject = SecurityUtils.getSubject();
        return subject == null ? false : subject.isPermittedAll(permissions);
    }

    /**
     * Description: 验证当前用户是否拥有以下任意一个权限
     *
     * @param permissions
     * @return
     */
    public static boolean hasAnyPermission(String[] permissions) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && permissions != null) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                if (permission != null && subject.isPermitted(permission.trim())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 检查是否有权限，若无则抛出异常。
     *
     * @see org.apache.shiro.subject.Subject#checkPermission(String permission)
     * @param permission
     * @throws AuthorizationException
     */
    public static void checkPermission(String permission) throws AuthorizationException {
        Subject subject = SecurityUtils.getSubject();
        if (subject == null) {
            throw new AuthorizationException("No permission as there is no subject bound.");
        }
        subject.checkPermission(permission);
    }

    /**
     * Description: 验证当前用户是否属于以下所有角色。请通过权限而不是角色做判断，比如hasPermission。
     *
     * @param roles
     * @return
     */
    @Deprecated
    public static boolean hasAllRoles(Collection<String> roles) {
        return SecurityUtils.getSubject().hasAllRoles(roles);
    }

    /**
     * Description: 验证当前用户是否属于以下任意一个角色。请通过权限而不是角色做判断，比如hasPermission。
     *
     * @param roleNames
     * @return
     */
    @Deprecated
    public static boolean hasAnyRoles(Collection<String> roleNames) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && roleNames != null) {
            for (String role : roleNames) {
                if (role != null && subject.hasRole(role)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Description: 清除指定用户的授权信息缓存。
     *
     * @param userId
     */
    public static void clearAuthzCache(String token) {
        AuthorityService userService = ApplicationContextUtil.getBean(AuthorityService.class);
        userService.clearAuthzCache(token);
    }

    /**
     * Description: 清除指定用户的认证信息缓存。
     *
     * @param userId
     */
    public static void clearAuthcCache(String token) {
        AuthorityService userService = ApplicationContextUtil.getBean(AuthorityService.class);
        userService.clearAuthcCache(token);
    }

    /**
     * Description: 获得静态资源地址
     *
     * @param userId
     */
    public static String getContextPath() {
        AuthorityService userService = ApplicationContextUtil.getBean(AuthorityService.class);
        return userService.getStaticPath()[0];
    }

    /**
     * Description: 获得FTP地址
     *
     * @return
     */
    public static String getStaticPath() {
        AuthorityService userService = ApplicationContextUtil.getBean(AuthorityService.class);
        return userService.getStaticPath()[1];
    }

    public static Subject getSubject() {
        try {
            return SecurityUtils.getSubject();
        } catch (Exception e) {
            LOGGER.warn("Failed to get Subject, maybe user is not login or session is lost:", e);
            return null;
        }
    }

    /**
     * Description: 清除指定用户的个人信息缓存。
     *
     * @param userId
     */
    public static void clearUserInfoCache(String token) {
        AuthorityService userService = ApplicationContextUtil.getBean(AuthorityService.class);
        userService.clearUserInfoCache(token);
    }

    /**
     * Description: 注销登陆
     *
     * @param token
     */
    public static void logout(String token) {
        AuthorityService userService = ApplicationContextUtil.getBean(AuthorityService.class);
        userService.clearUserInfoCache(token);
        userService.clearAuthcCache(token);
        userService.clearAuthzCache(token);
    }

    /**
     * Description: 获得菜单索引
     *
     * @param url
     * @return
     */
    public static Integer getMenuIndex(String url) {
        AuthorityService userService = ApplicationContextUtil.getBean(AuthorityService.class);
        Map<String, Integer> pages = userService.getPages();
        Map<Integer, Integer> menus = userService.getMenus();
        if (pages != null && menus != null) {
            Integer index = menus.get(pages.get(url));
            if (index != null) {
                return index;
            }
        }
        return 0;
    }

}
