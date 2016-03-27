package cn.dolphinsoft.hilife.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.dolphinsoft.hilife.common.authority.domain.UserInfo;

@Entity
@Table(name = "CUST_INFO")
public class CustUserInfo extends UserInfo {

    private static final long serialVersionUID = -8632114595674423302L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "CUST_IMAGE")
    private String custImage;

    @Column(name = "CUST_NAME")
    private String custName;

    @Column(name = "DEF_CAR_ID")
    private Integer defCarId;

    @Column(name = "SEX")
    private String sex = "0";

    @Column(name = "REGION_ID")
    private Integer regionId;

    @Column(name = "ADDRESS_ID")
    private Integer addressId;

    @Column(name = "USERNAME")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCustImage() {
        return custImage;
    }

    public void setCustImage(String custImage) {
        this.custImage = custImage;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getDefCarId() {
        return defCarId;
    }

    public void setDefCarId(Integer defCarId) {
        this.defCarId = defCarId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

}
