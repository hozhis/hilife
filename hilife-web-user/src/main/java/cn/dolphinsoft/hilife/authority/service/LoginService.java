package cn.dolphinsoft.hilife.authority.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.memcached.constant.CacheType;

/**
 * Class Name: LoginService Description: 登陆相关Service
 * 
 * @author hozhis
 *
 */
public interface LoginService {

    /**
     * Description: 发送短信
     *
     * @param loginId
     * @return
     */
    @CachePut(value = CacheType.MEMCACHED_FOR_SMS, key = "#loginId+'_loginSms'")
    String sendLoginSms(String loginId);

    /**
     * Description: 从缓存中取出短信密码
     *
     * @param loginId
     * @return
     */
    @Cacheable(value = CacheType.MEMCACHED_FOR_SMS, key = "#loginId+'_loginSms'")
    String getLoginSms(String loginId);

    /**
     * Description: 从缓存中清除短信密码
     *
     * @param loginId
     * @return
     */
    @CacheEvict(value = CacheType.MEMCACHED_FOR_SMS, key = "#loginId+'_loginSms'")
    void clearLoginSms(String loginId);

    /**
     * Description: 从数据库中清除token
     *
     * @param token
     */
    @Transactional(propagation = Propagation.REQUIRED)
    void clearToken(String token);

    /**
     * 
     * Description: 检测用户是否第一次登录
     *
     * @param loginId
     * @return
     */
    ResultDto<String> checkUser(String loginId);
}
