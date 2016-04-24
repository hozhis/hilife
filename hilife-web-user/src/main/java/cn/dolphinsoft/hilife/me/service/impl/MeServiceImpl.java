package cn.dolphinsoft.hilife.me.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.CustUserInfo;
import cn.dolphinsoft.hilife.common.repository.ICustUserInfoRepository;
import cn.dolphinsoft.hilife.me.dto.CustUserInfoDto;
import cn.dolphinsoft.hilife.me.service.MeService;

@Service
public class MeServiceImpl implements MeService {

    @Autowired
    private ICustUserInfoRepository userInfoRepository;

    @Override
    public CustUserInfoDto getUserInfo() {
        CustUserInfo custUserInfo = userInfoRepository.findByToken(AuthorityContext.getCurrentToken());
        Assert.notNull(custUserInfo);
        return ConverterService.convert(custUserInfo, CustUserInfoDto.class);
    }

}
