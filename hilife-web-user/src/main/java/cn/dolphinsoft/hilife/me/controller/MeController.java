package cn.dolphinsoft.hilife.me.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/me")
public class MeController {
    @RequestMapping(value = "/index")
    public String home(Model model) {
        return "me/index";
    }
}
