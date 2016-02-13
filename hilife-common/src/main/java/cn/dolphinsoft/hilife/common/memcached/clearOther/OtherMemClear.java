package cn.dolphinsoft.hilife.common.memcached.clearOther;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.dolphinsoft.hilife.common.memcached.clearOther.constant.SystemType;
import cn.dolphinsoft.hilife.common.util.AppConfigUtil;

/**
 * Class Name: OtherMemClear
 * 
 * Description: 清除其他子系统基础信息缓存
 * 
 * @author hozhis
 *
 */
@Component
public class OtherMemClear {
    private static final Logger LOGGER = LoggerFactory.getLogger(OtherMemClear.class);

    private static ConcurrentHashMap<String, MemcachedClient> clientMap = new ConcurrentHashMap<String, MemcachedClient>();

    /**
     * Description: 删除缓存
     *
     * @param type
     * @param value
     * @param key
     */
    public void clear(String type, String value, String key) {
        List<InetSocketAddress> addresses = getInetSocketAddress(type);
        if (!addresses.isEmpty()) {
            MemcachedClient client = clientMap.get(type);
            if (client == null) {
                MemcachedClientBuilder builder = new XMemcachedClientBuilder(addresses);
                try {
                    client = builder.build();
                    LOGGER.info(" Memcached 连接创建成功。type:{},key:{}", type, key);
                } catch (IOException e) {
                    LOGGER.error(" Memcached 连接创建失败。type:{},key:{}", type, key, e);
                }
                clientMap.putIfAbsent(type, client);
            }
            delete(client, value, key);
        } else {
            LOGGER.warn("缓存清除失败.type:{},key:{}", type, key);
        }
    }

    /**
     * Description: 删除缓存
     *
     * @param client
     * @param value
     * @param key
     */
    private void delete(MemcachedClient client, String value, String key) {
        try {
            key = getKey(value, key);
            client.deleteWithNoReply(key);
            LOGGER.info("缓存清除成功。key:{}", key);
        } catch (InterruptedException e) {
            LOGGER.error("删除 Memcached 缓存被中断。key:{}", key, e);
        } catch (MemcachedException e) {
            LOGGER.error("删除 Memcached 缓存错误。key:{}", key, e);
        }
    }

    /**
     * Description: 获得缓存地址
     *
     * @param type
     * @return
     */
    private List<InetSocketAddress> getInetSocketAddress(String type) {
        List<InetSocketAddress> addresses = new ArrayList<InetSocketAddress>();
        String ipConfig = null;
        String portConfig = null;

        switch (type) {
        case SystemType.PLATFORM:
            ipConfig = "platform.memcached.server.ips";
            portConfig = "platform.memcached.server.ports";
            break;
        case SystemType.SHOP:
            ipConfig = "shop.memcached.server.ips";
            portConfig = "shop.memcached.server.ports";
            break;
        case SystemType.SUPPLIER:
            ipConfig = "supplier.memcached.server.ips";
            portConfig = "supplier.memcached.server.ports";
            break;
        case SystemType.USER:
            ipConfig = "user.memcached.server.ips";
            portConfig = "user.memcached.server.ports";
            break;
        default:
            LOGGER.warn("Memcached清除缓存类型不存在");
            break;
        }
        if (ipConfig != null && portConfig != null) {
            String[] ips = AppConfigUtil.getConfig(ipConfig).split(";");
            String[] ports = AppConfigUtil.getConfig(portConfig).split(";");
            for (int i = 0; i < ips.length && i < ports.length; i++) {
                InetSocketAddress addr = new InetSocketAddress(ips[i].trim(), Integer.parseInt(ports[i].trim()));
                addresses.add(addr);
            }
        }
        return addresses;
    }

    /**
     * Description: 获得key
     *
     * @param value
     * @param key
     * @return
     */
    private String getKey(String value, String key) {
        return value + "_" + key;
    }
}
