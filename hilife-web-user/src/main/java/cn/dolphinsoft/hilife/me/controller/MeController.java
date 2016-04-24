package cn.dolphinsoft.hilife.me.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dolphinsoft.hilife.me.dto.CustUserInfoDto;
import cn.dolphinsoft.hilife.me.service.MeService;

@Controller
@RequestMapping(value = "/me")
public class MeController {

    @Autowired
    private MeService meService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Model model) {
        CustUserInfoDto dto = meService.getUserInfo();
        model.addAttribute("custImage", dto.getCustImage());
        return "me/index";
    }

    @RequestMapping(value = "/myinfo", method = RequestMethod.GET)
    public String myinfo(Model model) {
        CustUserInfoDto dto = meService.getUserInfo();
        model.addAttribute("myinfo", dto);
        return "me/myinfo";
    }
}
