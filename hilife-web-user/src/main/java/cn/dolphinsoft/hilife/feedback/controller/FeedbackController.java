package cn.dolphinsoft.hilife.feedback.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "feedback/index";
    }
}
