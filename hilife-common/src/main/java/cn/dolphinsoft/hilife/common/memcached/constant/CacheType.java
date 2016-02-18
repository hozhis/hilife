package cn.dolphinsoft.hilife.common.memcached.constant;

/**
 * Class Name: CacheType Description:定义了系统缓存类型<br>
 * 此处定义的缓存类型与xmemcached-context.xml文件中定义的cacheManager参数configMap的key一致
 * 
 * @author hozhis
 *
 */
public class CacheType {
    /**
     * 变量名: DEFAULT 描述: 默认缓存类型,在Memcached缓存时间为12个小时
     */
    public static final String DEFAULT = "DEFAULT";

    /**
     * 变量名: THREAD_LOCAL 描述: 仅在当前ThreadLocal缓存,不会同步到Memcached,在Request结束时,该缓存将被清除
     */
    public static final String THREAD_LOCAL = "THREAD_LOCAL";

    /**
     * 变量名: APPLICATIONAL 描述: 以最大时间缓存到Memcached
     */
    public static final String APPLICATIONAL = "APPLICATIONAL";

    /**
     * 变量名: MEMCACHED_ONLY 描述: 不使用本地缓存
     */
    public static final String MEMCACHED_ONLY = "MEMCACHED_ONLY";

    /**
     * 变量名: MEMCACHED_FOR_SMS 描述: 缓存短信验证码
     */
    public static final String MEMCACHED_FOR_SMS = "MEMCACHED_FOR_SMS";

}
