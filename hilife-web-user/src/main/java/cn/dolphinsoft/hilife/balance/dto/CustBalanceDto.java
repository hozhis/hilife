package cn.dolphinsoft.hilife.balance.dto;

import java.io.Serializable;

public class CustBalanceDto implements Serializable {

    private static final long serialVersionUID = 3447768165366377611L;

    private Integer balanceId;

    private Integer userId;

    private Integer balance;

    public Integer getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Integer balanceId) {
        this.balanceId = balanceId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

}
