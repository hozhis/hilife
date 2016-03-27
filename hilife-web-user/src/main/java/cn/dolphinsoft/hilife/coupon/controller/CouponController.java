package cn.dolphinsoft.hilife.coupon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/coupon")
public class CouponController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "coupon/index";
    }

    @RequestMapping(value = "/share", method = RequestMethod.GET)
    public String share(Model model) {
        return "coupon/share";
    }
    
    @RequestMapping(value = "/describe", method = RequestMethod.GET)
    public String couponDescribe(Model model) {
        return "coupon/describe";
    }
}
