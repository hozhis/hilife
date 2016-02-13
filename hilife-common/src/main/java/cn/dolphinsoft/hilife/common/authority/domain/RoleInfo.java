package cn.dolphinsoft.hilife.common.authority.domain;

import java.io.Serializable;

public class RoleInfo implements Serializable {

    private static final long serialVersionUID = -6701053677524576235L;

    private Integer roleId;

    private String role;

    private String description;

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

}
