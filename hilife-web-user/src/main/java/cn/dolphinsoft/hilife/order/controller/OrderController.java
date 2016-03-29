package cn.dolphinsoft.hilife.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "order/index";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam Integer orderId, Model model) {
        return "order/detail";
    }

    @RequestMapping(value = "/evaluate", method = RequestMethod.GET)
    public String evaluate(@RequestParam Integer orderId, Model model) {
        return "order/evaluate";
    }
}
