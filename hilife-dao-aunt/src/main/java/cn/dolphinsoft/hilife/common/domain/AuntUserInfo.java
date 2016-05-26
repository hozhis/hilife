package cn.dolphinsoft.hilife.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.dolphinsoft.hilife.common.authority.domain.UserInfo;

@Entity
@Table(name = "AUNT_INFO")
public class AuntUserInfo extends UserInfo {

    private static final long serialVersionUID = -8632114595674423302L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "TOKEN")
    private String token;

    @Column(name = "AUNT_IMAGE")
    private String auntImage;

    @Column(name = "AUNT_NAME")
    private String auntName;

    @Column(name = "SEX")
    private String sex = "0";

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

    public String getAuntImage() {
        return auntImage;
    }

    public void setAuntImage(String auntImage) {
        this.auntImage = auntImage;
    }

    public String getAuntName() {
        return auntName;
    }

    public void setAuntName(String auntName) {
        this.auntName = auntName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
