package cn.dolphinsoft.hilife.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.coupon.service.CouponService;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
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
}
