package cn.dolphinsoft.hilife.feedback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.dolphinsoft.hilife.feedback.dto.FeedbackDto;
import cn.dolphinsoft.hilife.feedback.service.FeedbackService;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        List<FeedbackDto> dtos = feedbackService.getFiveHotFeedback();
        model.addAttribute("feedbacks", dtos);
        return "feedback/index";
    }

    @RequestMapping(value = "/detail/{fbId}", method = RequestMethod.GET)
    public String detail(@PathVariable Integer fbId, Model model) {
        feedbackService.feedbackHasReviewed(fbId);
        return "feedback/detail";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addFeedback(Model model) {
        return "feedback/add";
    }

}
