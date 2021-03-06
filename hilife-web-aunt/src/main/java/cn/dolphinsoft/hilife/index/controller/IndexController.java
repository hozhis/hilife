package cn.dolphinsoft.hilife.index.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.dolphinsoft.hilife.index.dto.CustOrderDto;
import cn.dolphinsoft.hilife.index.service.OrderService;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model, @RequestParam String token) {
        List<CustOrderDto> dtos = orderService.findAllOrder(token);
        if (dtos != null && dtos.size() > 0) {
            model.addAttribute("orderNum", dtos.size());
        }
        return "index/index";
    }

}
