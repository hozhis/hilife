package cn.dolphinsoft.hilife.common.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.dolphinsoft.hilife.common.authority.domain.RoleInfo;

/**
 * Class Name: PlatformRoleInfo
 * 
 * Description: 平台角色表
 * 
 * @author hozhis
 *
 */
@Entity
@Table(name = "PLATFORM_ROLE_INFO")
public class PlatformRoleInfo extends RoleInfo {

    private static final long serialVersionUID = 24161575465561642L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "STATUS")
    private String status;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
