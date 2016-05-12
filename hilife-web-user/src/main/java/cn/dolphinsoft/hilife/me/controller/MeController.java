package cn.dolphinsoft.hilife.me.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
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
        model.addAttribute("citys", meService.getCitys());
        return "me/myinfo";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> saveUserinfo(@RequestBody CustUserInfoDto dto) {
        meService.saveUserinfo(dto);
        return ResultDtoFactory.toAck("保存成功");
    }

    @RequestMapping(value = "/getCitys", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Map<Integer, String>> getCitys() {
        return meService.getCitys();
    }

    @RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> uploadImage(@RequestParam MultipartFile file, @RequestParam String token) {
        return meService.uploadImage(file);
    }
}
