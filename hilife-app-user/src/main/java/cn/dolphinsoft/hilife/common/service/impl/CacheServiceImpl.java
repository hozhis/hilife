package cn.dolphinsoft.hilife.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dolphinsoft.hilife.common.domain.BasicPara;
import cn.dolphinsoft.hilife.common.repository.IBasicParaRepository;
import cn.dolphinsoft.hilife.common.service.CacheService;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private IBasicParaRepository basicParaRepository;

    @Override
    public List<BasicPara> findBasicParaByTypeId(Integer typeId) {
        return basicParaRepository.findByTypeId(typeId);
    }

}
