package cn.dolphinsoft.hilife.balance.dto;

import cn.dolphinsoft.hilife.common.dto.RequestDto;

public class CustTransactionDto extends RequestDto {

    private static final long serialVersionUID = 8295801183112251263L;

    private Integer transId;

    private Integer userId;

    private Integer orderId;

    private String transName;

    private String transTime;

    private Integer transMoney;

    private Integer balance;

    private Integer code;

    private String status;

    public Integer getTransId() {
        return transId;
    }

    public void setTransId(Integer transId) {
        this.transId = transId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getTransName() {
        return transName;
    }

    public void setTransName(String transName) {
        this.transName = transName;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public Integer getTransMoney() {
        return transMoney;
    }

    public void setTransMoney(Integer transMoney) {
        this.transMoney = transMoney;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
