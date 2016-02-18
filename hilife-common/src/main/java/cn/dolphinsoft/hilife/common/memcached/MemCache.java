package cn.dolphinsoft.hilife.common.memcached;

import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class Name: MemCache Description: TODO
 * 
 * @author hozhis
 *
 */
public class MemCache {
    private static Logger log = LoggerFactory.getLogger(MemCache.class);

    private final String name;

    private final int expire;

    private final MemcachedClient memcachedClient;

    public MemCache(String name, int expire, MemcachedClient memcachedClient) {
        this.name = name;
        this.expire = expire;
        this.memcachedClient = memcachedClient;
    }

    public Object get(String key) {
        Object value = null;
        try {
            key = this.getKey(key);
            value = memcachedClient.get(key);
            log.debug("获得Memcached缓存-" + key);
        } catch (TimeoutException e) {
            log.warn("获取 Memcached 缓存超时-" + key, e);
        } catch (InterruptedException e) {
            log.warn("获取 Memcached 缓存被中断-" + key, e);
        } catch (MemcachedException e) {
            log.warn("获取 Memcached 缓存错误-" + key, e);
        }
        return value;
    }

    public void put(String key, Object value) {
        if (null == value) {
            return;
        }
        try {
            key = this.getKey(key);
            memcachedClient.setWithNoReply(key, expire, value);
            log.debug("设置Memcached缓存-" + key);
        } catch (InterruptedException e) {
            log.warn("更新 Memcached 缓存被中断-" + key, e);
        } catch (MemcachedException e) {
            log.warn("更新 Memcached 缓存错误-" + key, e);
        }
    }

    public void clear() {
        // TODO 因为Tomcat服务器为集群,无法收集到所有的cache key,所以只能等待memcached服务器上的内容自然过期.
        log.warn("无法直接清空Memcached缓存");
    }

    public void delete(String key) {
        try {
            key = this.getKey(key);
            memcachedClient.deleteWithNoReply(key);
            log.debug("删除Memcached缓存-" + key);
        } catch (InterruptedException e) {
            log.warn("删除 Memcached 缓存被中断-" + key, e);
        } catch (MemcachedException e) {
            log.warn("删除 Memcached 缓存错误-" + key, e);
        }
    }

    private String getKey(String key) {
        return name + "_" + key;
    }

}
