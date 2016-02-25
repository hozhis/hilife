package cn.dolphinsoft.hilife.common.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import cn.dolphinsoft.hilife.common.domain.BasicPara;
import cn.dolphinsoft.hilife.common.memcached.constant.CacheType;

public interface CacheService {

    @Cacheable(value = CacheType.DEFAULT, key = "#typeId+'_BasicPara'")
    List<BasicPara> findBasicParaByTypeId(Integer typeId);

}
