package cn.dolphinsoft.hilife.life.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/life")
public class LifeController {
    
    @RequestMapping(value = "/index")
    public String home(Model model) {
        return "life/index";
    }
}
