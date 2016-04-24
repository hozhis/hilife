package cn.dolphinsoft.hilife.coupon.dto;

import java.io.Serializable;

public class CustCouponDto implements Serializable {

    private static final long serialVersionUID = 8578399849514927399L;

    private Integer id;

    private Integer userId;

    private Integer couponId;

    private String couponTitle;

    private Integer money;

    private Integer useMoneyLimit;

    private String useCondLimit;

    private String effectiveDate;

    private String expireDate;

    public String getCouponTitle() {
        return couponTitle;
    }

    public void setCouponTitle(String couponTitle) {
        this.couponTitle = couponTitle;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getUseMoneyLimit() {
        return useMoneyLimit;
    }

    public void setUseMoneyLimit(Integer useMoneyLimit) {
        this.useMoneyLimit = useMoneyLimit;
    }

    public String getUseCondLimit() {
        return useCondLimit;
    }

    public void setUseCondLimit(String useCondLimit) {
        this.useCondLimit = useCondLimit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

}
