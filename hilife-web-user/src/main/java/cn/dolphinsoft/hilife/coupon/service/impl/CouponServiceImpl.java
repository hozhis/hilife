package cn.dolphinsoft.hilife.coupon.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.domain.Coupon;
import cn.dolphinsoft.hilife.common.domain.CustCoupon;
import cn.dolphinsoft.hilife.common.domain.CustInviteUser;
import cn.dolphinsoft.hilife.common.domain.CustUserInfo;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.enumeration.BaseStatus;
import cn.dolphinsoft.hilife.common.enums.CouponType;
import cn.dolphinsoft.hilife.common.repository.ICouponRepository;
import cn.dolphinsoft.hilife.common.repository.ICustCouponRepository;
import cn.dolphinsoft.hilife.common.repository.ICustInviteUserRepository;
import cn.dolphinsoft.hilife.common.repository.ICustUserInfoRepository;
import cn.dolphinsoft.hilife.common.util.DateTimeUtil;
import cn.dolphinsoft.hilife.coupon.dto.CustCouponDto;
import cn.dolphinsoft.hilife.coupon.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private ICouponRepository couponRepository;

    @Autowired
    private ICustCouponRepository custCouponRepository;

    @Autowired
    private ICustUserInfoRepository custUserInfoRepository;

    @Autowired
    private ICustInviteUserRepository inviteUserRepository;

    @Override
    public String getInviteCode(String token) {
        CustUserInfo custUserInfo = custUserInfoRepository.findByToken(token);
        Assert.notNull(custUserInfo);
        return custUserInfo.getInviteCode();
    }

    @Override
    public List<CustCouponDto> getCouponList() {
        List<CustCouponDto> dtos = new ArrayList<>();
        List<Coupon> coupons = couponRepository.findByStatus(BaseStatus.EFFECT.getKey());
        if (coupons == null) {
            return null;
        }
        for (Coupon coupon : coupons) {
            List<CustCoupon> custCoupons = custCouponRepository
                    .findByUserIdAndCouponId(AuthorityContext.getCurrentUser().getUserId(), coupon.getCouponId());
            if (custCoupons != null && custCoupons.size() > 0) {
                CustCouponDto dto;
                for (int i = 0; i < custCoupons.size(); i++) {
                    dto = new CustCouponDto();
                    dto.setCouponId(coupon.getCouponId());
                    dto.setCouponTitle(coupon.getCouponTitle());
                    dto.setMoney(coupon.getMoney());
                    dto.setUseMoneyLimit(coupon.getUseMoneyLimit());
                    dto.setUseCondLimit(coupon.getUseCondLimit());
                    dto.setEffectiveDate(DateTimeUtil.parseDateToString(custCoupons.get(i).getEffectiveDate(),
                            DateTimeUtil.SIMPLE_YMD));
                    dto.setExpireDate(DateTimeUtil.parseDateToString(custCoupons.get(i).getExpireDate(),
                            DateTimeUtil.SIMPLE_YMD));
                    dtos.add(dto);
                }
            }
        }
        return dtos;
    }

    @Override
    public ResultDto<String> exchangeCoupon(String inviteCode) {
        Integer userId = AuthorityContext.getCurrentUser().getUserId();
        CustUserInfo custUserInfo = custUserInfoRepository.findByInviteCode(inviteCode);
        if (custUserInfo == null) {
            return ResultDtoFactory.toNack("此邀请码无效！");
        }
        CustInviteUser inviteUser = inviteUserRepository.findByUserId(userId);
        if (inviteUser != null) {
            return ResultDtoFactory.toNack("只限新用户！");
        }
        Integer inviteUserId = custUserInfo.getUserId();
        CustInviteUser user = new CustInviteUser();
        user.setUserId(userId);
        user.setInviteUserId(inviteUserId);
        inviteUserRepository.save(user);
        List<Coupon> coupons = couponRepository.findByTypeIdAndStatus(Integer.parseInt(CouponType.NEWUSER.getKey()),
                BaseStatus.EFFECT.getKey());
        CustCoupon custCoupon;
        for (Coupon coupon : coupons) {
            custCoupon = new CustCoupon();
            custCoupon.setUserId(userId);
            custCoupon.setCouponId(coupon.getCouponId());
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtil.SIMPLE_FMT);
            sdf.format(date);
            custCoupon.setEffectiveDate(date);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, 3);
            Date eDate = calendar.getTime();
            sdf.format(eDate);
            custCoupon.setExpireDate(eDate);
            custCouponRepository.save(custCoupon);
        }
        return ResultDtoFactory.toAck("兑换成功！");
    }

    @Override
    public Integer getExpCount() {
        return custCouponRepository.getExpCount(AuthorityContext.getCurrentUser().getUserId());
    }

}
