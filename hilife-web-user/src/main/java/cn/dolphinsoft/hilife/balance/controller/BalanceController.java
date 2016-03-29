package cn.dolphinsoft.hilife.balance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/balance")
public class BalanceController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        return "balance/index";
    }
    
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String balanceDetail(Model model) {
        return "balance/detail";
    }
    
    @RequestMapping(value = "/bank/index", method = RequestMethod.GET)
    public String bank(Model model) {
        return "balance/bank";
    }
}
