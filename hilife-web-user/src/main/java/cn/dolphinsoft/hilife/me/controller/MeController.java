package cn.dolphinsoft.hilife.me.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/me")
public class MeController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Model model) {
        return "me/index";
    }

    @RequestMapping(value = "/myinfo", method = RequestMethod.GET)
    public String myinfo(Model model) {
        return "me/myinfo";
    }
}
