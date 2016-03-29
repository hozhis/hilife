package cn.dolphinsoft.hilife.authority.dto;

import cn.dolphinsoft.hilife.common.dto.RequestDto;

/**
 * 
 * Class Name: PlatformRoleInfoDto
 * 
 * Description: 平台角色信息表
 * 
 * @author hozhis
 *
 */
public class PlatformRoleInfoDto extends RequestDto {

    private static final long serialVersionUID = 3159603555041226450L;

    private Integer roleId;

    private String role;

    private String description;

    private String createDate;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
