package cn.dolphinsoft.hilife.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.dolphinsoft.hilife.common.authority.domain.RoleFunction;

/**
 * Class Name: PlatformUserRole
 * 
 * Description: 平台用户角色关联表
 * 
 * @author hozhis
 *
 */
@Entity
@Table(name = "PLATFORM_USER_ROLE")
public class PlatformUserRole extends RoleFunction {

    private static final long serialVersionUID = -1509388999939055998L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "USER_ID")
    private Integer userId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

}
