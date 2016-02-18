package cn.dolphinsoft.hilife.common.memcached;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import net.rubyeye.xmemcached.MemcachedClient;

import org.springframework.cache.Cache;
import org.springframework.cache.transaction.AbstractTransactionSupportingCacheManager;

/**
 * Class Name: MemcachedCacheManager Description: TODO
 * 
 * @author hozhis
 *
 */
public class MemcachedCacheManager extends AbstractTransactionSupportingCacheManager {

    private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();

    private Map<String, Integer> expireMap = new ConcurrentHashMap<String, Integer>();

    private MemcachedClient memcachedClient;

    @Override
    protected Collection<? extends Cache> loadCaches() {
        return cacheMap.values();
    }

    @Override
    public Cache getCache(String name) {
        Cache cache = cacheMap.get(name);
        if (null == cache) {
            Integer expire = expireMap.get(name);
            if (null == expire) {
                expire = 0;
                expireMap.put(name, expire);
            }
            cache = new MemcachedCache(name, expire.intValue(), memcachedClient);
            cacheMap.put(name, cache);
        }
        return cache;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }

    public void setConfigMap(Map<String, Integer> configMap) {
        this.expireMap = configMap;
    }

}
