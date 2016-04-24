package cn.dolphinsoft.hilife.city.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.city.dto.CityDto;
import cn.dolphinsoft.hilife.city.service.RegionService;
import cn.dolphinsoft.hilife.common.dto.ResultDto;

@Controller
@RequestMapping("/city")
public class RegionController {
    
    @Autowired
    private RegionService regionService;
    
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "city/index";
    }
    
    @RequestMapping(value = "/getCity", method = RequestMethod.GET)
    @ResponseBody
    public ResultDto<List<CityDto>> getCity(Model model){
        return regionService.getCity();
    }
}
