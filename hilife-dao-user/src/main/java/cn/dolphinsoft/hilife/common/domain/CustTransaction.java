package cn.dolphinsoft.hilife.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_TRANSACTION")
public class CustTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TRANS_ID")
    private Integer transId;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "ORDER_ID")
    private Integer orderId;

    @Column(name = "TRANS_NAME")
    private String transName;

    @Column(name = "TRANS_TIME")
    private Date transTime;

    @Column(name = "TRANS_MONEY")
    private Integer transMoney;

    @Column(name = "BALANCE")
    private Integer balance;

    @Column(name = "CODE")
    private Integer code;

    @Column(name = "STATUS")
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

    public Date getTransTime() {
        return transTime;
    }

    public void setTransTime(Date transTime) {
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
