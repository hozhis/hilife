package cn.dolphinsoft.hilife.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.order.dto.CustOrderSearchDto;
import cn.dolphinsoft.hilife.order.service.CustOrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private CustOrderService custOrderService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "order/index";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam Integer orderId, Model model) {
        return "order/detail";
    }

    @RequestMapping(value = "/evaluate", method = RequestMethod.GET)
    public String evaluate(@RequestParam Integer orderId, Model model) {
        return "order/evaluate";
    }

    @RequestMapping(value = "/searchOrder", method = RequestMethod.POST)
    @ResponseBody
    public CustOrderSearchDto searchByOrderStatus(@RequestBody CustOrderSearchDto dto) {
        custOrderService.searchCustOrder(dto);
        return dto;
    }
}
