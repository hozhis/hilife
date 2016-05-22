package cn.dolphinsoft.hilife.balance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dolphinsoft.hilife.balance.dto.CustTransactionSearchDto;
import cn.dolphinsoft.hilife.balance.service.BalanceService;

@Controller
@RequestMapping("/balance")
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        Integer balance = balanceService.getBalance();
        model.addAttribute("balance", balance);
        return "balance/index";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String balanceDetail(Model model) {
        Integer balance = balanceService.getBalance();
        model.addAttribute("balance", balance);
        return "balance/detail";
    }

    @RequestMapping(value = "/bank/index", method = RequestMethod.GET)
    public String bank(Model model) {
        return "balance/bank";
    }

    @RequestMapping(value = "/searchTransaction", method = RequestMethod.POST)
    @ResponseBody
    public CustTransactionSearchDto searchTransaction(@RequestBody CustTransactionSearchDto dto) {
        balanceService.searchTransaction(dto);
        return dto;
    }
}
