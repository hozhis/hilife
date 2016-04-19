package cn.dolphinsoft.hilife.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Model model) {
        return "product/index";
    }
}
