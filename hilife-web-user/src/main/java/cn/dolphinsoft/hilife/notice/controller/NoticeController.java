package cn.dolphinsoft.hilife.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "notice/index";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam Integer noticeId, Model model) {
        return "notice/detail";
    }

}
