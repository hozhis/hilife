package cn.dolphinsoft.hilife.balance.service;

import cn.dolphinsoft.hilife.balance.dto.CustTransactionSearchDto;

public interface BalanceService {

    Integer getBalance();

    void searchTransaction(CustTransactionSearchDto dto);

}
