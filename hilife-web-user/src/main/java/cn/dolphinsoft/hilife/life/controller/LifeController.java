package cn.dolphinsoft.hilife.life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.common.dto.BasicParaDto;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.life.dto.ProductPromoteDto;
import cn.dolphinsoft.hilife.life.service.LifeService;

@Controller
@RequestMapping(value = "/life")
public class LifeController {

    @Autowired
    private LifeService lifeservice;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String home(Model model) {
        List<ProductPromoteDto> dtos = lifeservice.getPromoteProduct();
        model.addAttribute("promotes", dtos);
        List<BasicParaDto> carousels = lifeservice.getCarouselPictures();
        model.addAttribute("carousels", carousels);
        return "life/index";
    }

    @RequestMapping(value = "/getCarouselPictures", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto<List<BasicParaDto>> getCarouselPictures() {
        List<BasicParaDto> dtos = lifeservice.getCarouselPictures();
        return ResultDtoFactory.toAck("success", dtos);
    }
}
