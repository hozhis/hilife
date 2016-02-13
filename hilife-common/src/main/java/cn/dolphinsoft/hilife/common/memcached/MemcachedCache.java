package cn.dolphinsoft.hilife.common.memcached;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import cn.dolphinsoft.hilife.common.memcached.constant.CacheType;

/**
 * Class Name: MemcachedCache Description: TODO
 * 
 * @author hozhis
 *
 */
public class MemcachedCache implements Cache {

    private final String name;

    private final MemcachedClient memcachedClient;

    private final MemCache memCache;

    private final ThreadLocal<Map<String, Object>> localCache = new ThreadLocal<>();

    private final Map<String, Object> applicationalCache = new ConcurrentHashMap<>();

    private final String cacheType;

    private static final int MAX_EXPIRE_TIME = 60 * 60 * 24 * 30;

    private static final String APPLICATIONAL_CACHE_UPDATED_KEY = "APPLICATIONAL_CACHE_UPDATED";

    private String applicationalLastUpdateTime = "";

    private static final Logger LOGGER = LoggerFactory.getLogger(MemcachedCache.class);

    public MemcachedCache(String name, int expire, MemcachedClient memcachedClient) {
        this.name = name;
        this.memcachedClient = memcachedClient;
        cacheType = expire < 0 || expire > MAX_EXPIRE_TIME ? CacheType.THREAD_LOCAL : name;
        this.memCache = hasMemcacheCache() ? new MemCache(name, expire, memcachedClient) : null;
    }

    private boolean hasLocalCache() {
        return !CacheType.MEMCACHED_ONLY.equals(cacheType) && !CacheType.DEFAULT.equals(cacheType);
    }

    private boolean hasMemcacheCache() {
        return !CacheType.THREAD_LOCAL.equals(cacheType);
    }

    @Override
    public void clear() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("清空缓存:" + cacheType);
        }
        // 如果本地有缓存,则清理本地缓存
        if (hasLocalCache() && null != localCache.get()) {
            localCache.get().clear();
        }
        // applicationalCache也全部清掉
        applicationalCache.clear();
        // 本地缓存不需要清理Memcached
        if (hasMemcacheCache()) {
            memCache.clear();
        }
        // 对于Applicational级别的缓存,需要通知其他应用缓存已更新
        if (CacheType.APPLICATIONAL.equals(cacheType)) {
            memCache.put(APPLICATIONAL_CACHE_UPDATED_KEY, String.valueOf(System.currentTimeMillis()));
        }
    }

    @Override
    public void evict(Object key) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("从缓存中消除:缓存类型:" + cacheType + "\t缓存key:" + key);
        }
        if (hasLocalCache() && null != localCache.get()) {
            localCache.get().remove(key);
        }
        switch (cacheType) {
        case CacheType.DEFAULT:
            memCache.delete(key.toString());
            break;
        case CacheType.THREAD_LOCAL:
            // 本地缓存不需要从Memcached中清除
            break;
        case CacheType.APPLICATIONAL:
            applicationalCache.remove(key);
            memCache.delete(key.toString());
            // 对于Applicational级别的缓存,需要通知其他应用缓存已更新
            memCache.put(APPLICATIONAL_CACHE_UPDATED_KEY, System.currentTimeMillis());
            break;
        case CacheType.MEMCACHED_ONLY:
            memCache.delete(key.toString());
            break;
        case CacheType.MEMCACHED_FOR_SMS:
            memCache.delete(key.toString());
            break;
        default:
            break;
        }
    }

    @Override
    public ValueWrapper get(Object key) {
        if (hasLocalCache() && null != localCache.get()) {
            Object value = localCache.get().get(key);
            if (null != value) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(
                            "从本地缓存中取出数据:缓存类型:" + cacheType + "\t缓存key:" + key + "\t缓存value:" + String.valueOf(value));
                }
                return new SimpleValueWrapper(value);
            }
        }
        if (CacheType.APPLICATIONAL.equals(cacheType)) {
            Object lastUpdateTime = memCache.get(APPLICATIONAL_CACHE_UPDATED_KEY);
            // 如果Memcached返回的updateTime和当前缓存的一样,则使用本地缓存的对象
            if (null == lastUpdateTime || lastUpdateTime.equals(applicationalLastUpdateTime)) {
                if (applicationalCache.containsKey(key)) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("从缓存中取出数据:缓存类型:" + cacheType + "\t缓存key:" + key + "\t缓存value:"
                                + applicationalCache.get(key));
                    }
                    return new SimpleValueWrapper(applicationalCache.get(key));
                }
            } else {
                // 否则清除本地缓存
                applicationalCache.clear();
                // 并更新时间戳
                applicationalLastUpdateTime = lastUpdateTime.toString();
            }
        }
        // 对于非本地缓存类型,尝试去Memcached服务器取新的数据
        if (hasMemcacheCache()) {
            Object value = memCache.get(key.toString());
            if (null != value) {
                if (hasLocalCache()) {
                    // 将对象缓存至当前线程
                    if (null == localCache.get()) {
                        localCache.set(new HashMap<String, Object>());
                    }
                    localCache.get().put(key.toString(), value);
                }
                // 对于Applicational缓存类型,需要将对象缓存至本地
                if (CacheType.APPLICATIONAL.equals(cacheType)) {
                    applicationalCache.put(key.toString(), value);
                }
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("从缓存中取出数据:缓存类型:" + cacheType + "\t缓存key:" + key + "\t缓存value:" + value);
                }
                return new SimpleValueWrapper(value);
            }
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("缓存中未找到数据:缓存类型:" + cacheType + "\t缓存key:" + key);
        }
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public MemcachedClient getNativeCache() {
        return this.memcachedClient;
    }

    @Override
    public void put(Object key, Object value) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("设置缓存:缓存类型:" + cacheType + "\t缓存key:" + key + "\t缓存value:" + value);
        }
        if (hasLocalCache()) {
            // 将对象缓存至当前线程
            if (null == localCache.get()) {
                localCache.set(new HashMap<String, Object>());
            }
            localCache.get().put(key.toString(), value);
        }
        // 对于非本地缓存,将对象存至数据库
        if (hasMemcacheCache()) {
            memCache.put(key.toString(), value);
        }
        // 对于Applicational缓存类型需要将对象缓存至本地
        if (CacheType.APPLICATIONAL == cacheType) {
            applicationalCache.put(key.toString(), value);
        }
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        return null;
    }

}
