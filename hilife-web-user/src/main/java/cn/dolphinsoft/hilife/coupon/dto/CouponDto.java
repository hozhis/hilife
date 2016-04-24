package cn.dolphinsoft.hilife.coupon.dto;

import java.io.Serializable;
import java.util.Date;

public class CouponDto implements Serializable {

    private static final long serialVersionUID = -4241610301548052910L;

    private Integer couponId;

    private String couponTitle;

    private Integer money;

    private Integer useMoneyLimit;

    private String useCondLimit;

    private Date createDate;

    private String status;

    private Integer typeId;

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
