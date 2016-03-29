package cn.dolphinsoft.hilife.authority.dto.upstream;

import java.util.List;

import cn.dolphinsoft.hilife.common.dto.RequestDto;

/**
 * Class Name: PlatformUserInfoDetailDto
 * 
 * Description: 平台用户管理 显示详情DTO
 * 
 * @author hozhis
 *
 */
public class PlatformUserInfoDetailDto extends RequestDto {

    private static final long serialVersionUID = -2862011742980971936L;

    private Integer userId;

    private String loginId;

    /**
     * 新密码 若用户有修改密码生成新密码，则新密码不等于NULL。否则新密码为NULL
     */
    private String password;

    private String userName;

    private String roles;

    /**
     * 旧密码
     */
    private String oldPassword;

    private Long phone;

    private String email;

    private String remark;

    private List<String> listSuppliers;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<String> getListSuppliers() {
        return listSuppliers;
    }

    public void setListSuppliers(List<String> listSuppliers) {
        this.listSuppliers = listSuppliers;
    }

}
