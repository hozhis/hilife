package cn.dolphinsoft.hilife.life.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/life")
public class LifeController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Model model) {
        return "life/index";
    }

    @RequestMapping(value = "/goods/index", method = RequestMethod.GET)
    public String goodsIndex(@RequestParam Integer onsaleType, Model model) {
        return "goods/index";
    }

    @RequestMapping(value = "/goods/detail", method = RequestMethod.GET)
    public String goodsDetail(@RequestParam Integer productId, Model model) {
        return "goods/detail";
    }

    @RequestMapping(value = "/goods/shopcart", method = RequestMethod.GET)
    public String shopcart(Model model) {
        return "goods/shopcart";
    }
}
