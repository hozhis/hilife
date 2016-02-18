package cn.dolphinsoft.hilife.common.memcached;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;

import cn.dolphinsoft.hilife.common.exception.HiLifeException;

/**
 * Class Name: MemClientConfigure Description: TODO
 * 
 * @author hozhis
 *
 */
public class MemClientConfigure {
    @Autowired
    private PropertiesFactoryBean propertiesFactory;

    private Properties prop = null;

    private static final String KEY_SERVER_IPS = "memcached.server.ips";

    private static final String KEY_SERVER_PORTS = "memcached.server.ports";

    private static final String KEY_SERVER_WEIGHTS = "memcached.server.weights";

    private static final String KEY_POOL_SIZE = "memcached.connection.pool.size";

    public List<InetSocketAddress> getSocketAddressList() {
        try {
            if (prop == null) {
                prop = propertiesFactory.getObject();
            }
            String[] ips = prop.getProperty(KEY_SERVER_IPS).split(";");
            String[] ports = prop.getProperty(KEY_SERVER_PORTS).split(";");
            List<InetSocketAddress> list = new ArrayList<>();
            for (int i = 0; i < ips.length && i < ports.length; i++) {
                InetSocketAddress addr = new InetSocketAddress(ips[i].trim(), Integer.parseInt(ports[i].trim()));
                list.add(addr);
            }
            return list;
        } catch (IOException e) {
            throw new HiLifeException(e);
        }
    }

    public int[] getWeightList() {
        try {
            if (prop == null) {
                prop = propertiesFactory.getObject();
            }
            String[] weightStrings = prop.getProperty(KEY_SERVER_WEIGHTS).split(";");
            int[] weights = new int[weightStrings.length];
            for (int i = 0; i < weights.length; i++) {
                weights[i] = Integer.parseInt(weightStrings[i]);
            }
            return weights;
        } catch (IOException e) {
            throw new HiLifeException(e);
        }
    }

    public int getPoolSize() {
        try {
            if (prop == null) {
                prop = propertiesFactory.getObject();
            }
            return Integer.parseInt(prop.getProperty(KEY_POOL_SIZE));
        } catch (IOException e) {
            throw new HiLifeException(e);
        }
    }
}
