package cn.dolphinsoft.hilife.life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        return "life/index";
    }

    @RequestMapping(value = "/goods/index", method = RequestMethod.GET)
    public String goodsIndex(@RequestParam Integer onsaleType, Model model) {
        return "goods/index";
    }

    @RequestMapping(value = "/goods/detail", method = RequestMethod.GET)
    public String goodsDetail(@RequestParam Integer productId, Model model) {
        return "goods/detail";
    }

    @RequestMapping(value = "/goods/shopcart", method = RequestMethod.GET)
    public String shopcart(Model model) {
        return "goods/shopcart";
    }
    
    @RequestMapping(value = "/getCarouselPictures", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto<List<BasicParaDto>> getCarouselPictures() {
        List<BasicParaDto> dtos = lifeservice.getCarouselPictures();
        return ResultDtoFactory.toAck("success", dtos);
    }
}
