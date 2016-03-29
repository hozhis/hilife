package cn.dolphinsoft.hilife.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import cn.dolphinsoft.hilife.common.authority.domain.UserInfo;

/**
 * Class Name: PlatformUserInfo
 * 
 * Description: 平台用户信息
 * 
 * @author hozhis
 *
 */
@Entity
@Table(name = "PLATFORM_USER_INFO")
public class PlatformUserInfo extends UserInfo {

    private static final long serialVersionUID = -8594331362983543923L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @NotBlank
    @Column(name = "LOGIN_ID")
    private String loginId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "TOKEN")
    private String token;

    @NotBlank
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "PHONE")
    private Long phone;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "REMARK")
    private String remark;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
