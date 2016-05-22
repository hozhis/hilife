package cn.dolphinsoft.hilife.feedback.service;

import java.util.List;

import cn.dolphinsoft.hilife.feedback.dto.FeedbackDto;

public interface FeedbackService {

    List<FeedbackDto> getFiveHotFeedback();

    void feedbackHasReviewed(Integer fbId);
}
