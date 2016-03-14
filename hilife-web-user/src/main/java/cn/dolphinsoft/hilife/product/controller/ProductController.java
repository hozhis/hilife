package cn.dolphinsoft.hilife.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.product.dto.ServiceItemDto;

@Controller
@RequestMapping(value = "/product")
public class ProductController {

    @RequestMapping(value = "/index")
    public String home(Model model) {
        return "product/index";
    }

    @RequestMapping(value = "/service/{serviceId}")
    public String serviceOrder(@PathVariable("serviceId") String id, Model model) {
        model.addAttribute("serviceId", id);
        return "product/serviceOrder";
    }

    @RequestMapping(value = "/order/submit", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> serviceSubmit(@RequestBody ServiceItemDto dto) {
        return ResultDtoFactory.toAck("");
    }
}
