package cn.dolphinsoft.hilife.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUPON")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPON_ID")
    private Integer couponId;

    @Column(name = "COUPON_TITLE")
    private String couponTitle;

    @Column(name = "MONEY")
    private Integer money;

    @Column(name = "USE_MONEY_LIMIT")
    private Integer useMoneyLimit;

    @Column(name = "USE_COND_LIMIT")
    private String useCondLimit;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "TYPE_ID")
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
