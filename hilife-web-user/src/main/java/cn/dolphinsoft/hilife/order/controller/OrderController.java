package cn.dolphinsoft.hilife.order.controller;

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
import cn.dolphinsoft.hilife.me.dto.CustUserInfoDto;
import cn.dolphinsoft.hilife.me.service.MeService;
import cn.dolphinsoft.hilife.order.dto.CustOrderDto;
import cn.dolphinsoft.hilife.order.dto.CustOrderSearchDto;
import cn.dolphinsoft.hilife.order.service.CustOrderService;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private CustOrderService custOrderService;

    @Autowired
    private MeService meService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "order/index";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam Integer orderId, Model model) {
        CustOrderDto dto = custOrderService.showOrderDetail(orderId);
        model.addAttribute("order", dto);
        CustUserInfoDto userInfo = meService.getUserInfo();
        model.addAttribute("username", userInfo.getUsername());
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

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> cancelOrder(@RequestParam Integer orderId) {
        custOrderService.cancelOrder(orderId);
        return ResultDtoFactory.toAck("取消成功");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultDto<String> deleteOrder(@RequestParam Integer orderId) {
        custOrderService.deleteOrder(orderId);
        return ResultDtoFactory.toAck("删除成功");
    }
}
