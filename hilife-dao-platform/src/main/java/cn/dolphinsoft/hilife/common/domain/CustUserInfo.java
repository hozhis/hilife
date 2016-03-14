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

    @Column(name = "LOTTERY_TIME")
    private Integer lotteryTime = 0;

    @Column(name = "SEX")
    private String sex = "0";

    @Column(name = "REGION_ID")
    private Integer regionId;

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

    public Integer getLotteryTime() {
        return lotteryTime;
    }

    public void setLotteryTime(Integer lotteryTime) {
        this.lotteryTime = lotteryTime;
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

}
