package cn.dolphinsoft.hilife.common.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_ADDRESS")
public class CustAddress implements Serializable {

    private static final long serialVersionUID = 2623141543522401634L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "ADDRESS_NAME")
    private String addressName;

    @Column(name = "STATUS")
    private String status = "1";

    @Column(name = "PHONE")
    private String phone;

    @Column(name="CONSIGNEE")
    private String consignee;
    
    @Column(name = "DEF")
    private Integer def = 0;// 默认不选中

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }
}
