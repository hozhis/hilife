package cn.dolphinsoft.hilife.coupon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.dolphinsoft.hilife.common.domain.CustUserInfo;
import cn.dolphinsoft.hilife.common.repository.ICustUserInfoRepository;
import cn.dolphinsoft.hilife.coupon.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private ICustUserInfoRepository custUserInfoRepository;

    @Override
    public String getInviteCode(String token) {
        CustUserInfo custUserInfo = custUserInfoRepository.findByToken(token);
        Assert.notNull(custUserInfo);
        return custUserInfo.getInviteCode();
    }

}
