package cn.dolphinsoft.hilife.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Model model) {
        return "order/index";
    }

}
