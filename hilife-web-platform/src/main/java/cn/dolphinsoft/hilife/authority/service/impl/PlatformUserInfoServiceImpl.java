package cn.dolphinsoft.hilife.authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dolphinsoft.hilife.authority.service.PlatformUserInfoService;
import cn.dolphinsoft.hilife.common.domain.PlatformUserInfo;
import cn.dolphinsoft.hilife.common.repository.PlatformUserInfoRepository;

@Service
public class PlatformUserInfoServiceImpl implements PlatformUserInfoService {

    @Autowired
    private PlatformUserInfoRepository platformUserInfoRepository;

    @Override
    public PlatformUserInfo find(Integer userId) {
        return platformUserInfoRepository.findOne(userId);
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public void update(PlatformUserInfo platformUserInfo) {
        platformUserInfoRepository.update(platformUserInfo.getEmail(), platformUserInfo.getRemark(),
                platformUserInfo.getUserId(), platformUserInfo.getPassword());
    }

}
