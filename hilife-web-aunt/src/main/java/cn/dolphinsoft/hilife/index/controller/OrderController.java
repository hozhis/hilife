package cn.dolphinsoft.hilife.index.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.index.dto.CustOrderDto;
import cn.dolphinsoft.hilife.index.service.OrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model,@RequestParam String token) {
        List<CustOrderDto> dtos = orderService.findAllOrder(token);
        if (dtos != null && dtos.size() > 0) {
            model.addAttribute("orders", dtos);
        }
        return "order/index";
    }

    @RequestMapping(value = "/startOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> startOrder(@RequestBody CustOrderDto dto) {
        orderService.startService(dto);
        return ResultDtoFactory.toAck("已开始计时");
    }

    @RequestMapping(value = "/finishOrder", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> finishOrder(@RequestBody CustOrderDto dto) {
        orderService.finishService(dto);
        return ResultDtoFactory.toAck("已结束计时");
    }

}
