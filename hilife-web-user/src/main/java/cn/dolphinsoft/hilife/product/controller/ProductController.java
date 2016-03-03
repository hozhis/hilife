package cn.dolphinsoft.hilife.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @RequestMapping(value = "/index")
    public String home(Model model) {
        return "product/index";
    }
}
