package cn.dolphinsoft.hilife.common.authority.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import cn.dolphinsoft.hilife.common.authority.domain.FunctionInfo;
import cn.dolphinsoft.hilife.common.authority.domain.RoleInfo;
import cn.dolphinsoft.hilife.common.authority.domain.UserInfo;
import cn.dolphinsoft.hilife.common.memcached.constant.CacheType;

/**
 * Class Name: AuthorityService Description: 权限验证Service
 * 
 * @author hozhis
 *
 */
public interface AuthorityService {

    /**
     * Description: 根据Token获得用户信息
     *
     * @param token
     * @return
     */
    @Cacheable(value = CacheType.DEFAULT, key = "#token + '_UserInfo'")
    public UserInfo findUserInfoByToken(String token);

    /**
     * Description: 验证登陆信息
     *
     * @param loginId
     * @param password
     * @return
     */
    public UserInfo authcUser(String loginId, String password);

    /**
     * Description: 保存用户信息
     *
     * @param userInfo
     */
    public void saveUserInfo(UserInfo userInfo);

    /**
     * Description: 根据Token获得用户角色信息
     *
     * @param token
     * @return
     */
    public List<RoleInfo> findRoleInfosByToken(String token);

    /**
     * Description: 根据角色获得相关权限
     *
     * @param roleIds
     * @return
     */
    public List<FunctionInfo> findFunctionsByRoleIds(Iterable<Integer> roleIds);

    /**
     * Description: 清除指定用户的个人信息缓存。
     *
     * @param userId
     */
    @CacheEvict(value = CacheType.DEFAULT, key = "#token+ '_UserInfo'")
    void clearUserInfoCache(String token);

    /**
     * Description: 清除指定用户的认证信息缓存。
     *
     * @param userId
     */
    @CacheEvict(value = CacheType.DEFAULT, key = "#token+ '_SimpleAuthenticationInfo'")
    void clearAuthcCache(String token);

    /**
     * Description: 清除指定用户的授权信息缓存。
     *
     * @param userId
     */
    @CacheEvict(value = CacheType.DEFAULT, key = "#token+ '_SimpleAuthorizationInfo'")
    void clearAuthzCache(String token);

    /**
     * Description: 获得静态资源地址
     *
     * @return
     */
    @Cacheable(value = CacheType.DEFAULT, key = "'_StaticPath'")
    String[] getStaticPath();

    /**
     * Description: 获得菜单列表 Map<functionId,priority>
     *
     * @return
     */
    @Cacheable(value = CacheType.DEFAULT, key = "'_Menus'")
    Map<Integer, Integer> getMenus();

    /**
     * Description: 获得页面列表 Map<url,MenusId>
     *
     * @return
     */
    @Cacheable(value = CacheType.DEFAULT, key = "'_Pages'")
    Map<String, Integer> getPages();

}
