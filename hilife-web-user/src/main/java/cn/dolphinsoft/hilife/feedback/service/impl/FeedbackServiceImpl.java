package cn.dolphinsoft.hilife.feedback.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.dolphinsoft.hilife.common.converter.ConverterService;
import cn.dolphinsoft.hilife.common.domain.Feedback;
import cn.dolphinsoft.hilife.common.repository.IFeedbackRepository;
import cn.dolphinsoft.hilife.feedback.dto.FeedbackDto;
import cn.dolphinsoft.hilife.feedback.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private IFeedbackRepository feedbackRepository;

    @Override
    public List<FeedbackDto> getFiveHotFeedback() {
        List<Feedback> feedbacks = feedbackRepository.findHotFeedback();
        List<FeedbackDto> dtos = new ArrayList<>();
        for (Feedback feedback : feedbacks) {
            FeedbackDto dto = ConverterService.convert(feedback, FeedbackDto.class);
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional
    @Override
    public void feedbackHasReviewed(Integer fbId) {
        feedbackRepository.hasReviewed(fbId);
    }

}
