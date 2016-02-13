package cn.dolphinsoft.hilife.common.memcached;

import net.rubyeye.xmemcached.XMemcachedClientBuilder;

/**
 * Class Name: MemClientBuilder Description: TODO
 * 
 * @author hozhis
 *
 */
public class MemClientBuilder extends XMemcachedClientBuilder {
    public MemClientBuilder(MemClientConfigure conf) {
        super(conf.getSocketAddressList(), conf.getWeightList());
        this.setConnectionPoolSize(conf.getPoolSize());
    }
}
