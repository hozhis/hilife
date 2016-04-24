package cn.dolphinsoft.hilife.coupon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.coupon.dto.CustCouponDto;
import cn.dolphinsoft.hilife.coupon.service.CouponService;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        List<CustCouponDto> dtos = couponService.getCouponList();
        Assert.notNull(dtos);
        model.addAttribute("coupons", dtos);
        model.addAttribute("expCount", couponService.getExpCount());
        return "coupon/index";
    }

    @RequestMapping(value = "/share", method = RequestMethod.GET)
    public String share(Model model) {
        String inviteCode = couponService.getInviteCode(AuthorityContext.getCurrentToken());
        model.addAttribute("inviteCode", inviteCode);
        return "coupon/share";
    }

    @RequestMapping(value = "/describe", method = RequestMethod.GET)
    public String couponDescribe(Model model) {
        return "coupon/describe";
    }

    @RequestMapping(value = "/getCouponList", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto<List<CustCouponDto>> getCouponList() {
        return ResultDtoFactory.toAck("success", couponService.getCouponList());
    }

    @RequestMapping(value = "/exchange", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto<String> exchangeCoupon(@RequestParam String inviteCode) {
        return couponService.exchangeCoupon(inviteCode);
    }
}
