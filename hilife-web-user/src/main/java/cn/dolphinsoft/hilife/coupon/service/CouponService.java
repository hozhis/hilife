package cn.dolphinsoft.hilife.coupon.service;

import java.util.List;


import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.coupon.dto.CustCouponDto;

public interface CouponService {

    String getInviteCode(String token);

    List<CustCouponDto> getCouponList();
    
    ResultDto<String> exchangeCoupon(String inviteCode);
    
    Integer getExpCount();
}
