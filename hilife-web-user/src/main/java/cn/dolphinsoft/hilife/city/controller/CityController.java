package cn.dolphinsoft.hilife.city.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/city")
public class CityController {
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "city/index";
    }
}
