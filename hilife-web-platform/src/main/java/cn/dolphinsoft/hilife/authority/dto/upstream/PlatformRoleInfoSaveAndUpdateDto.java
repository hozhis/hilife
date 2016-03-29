package cn.dolphinsoft.hilife.authority.dto.upstream;

import java.util.List;

import cn.dolphinsoft.hilife.common.dto.RequestDto;

/**
 * Class Name: UserRoleSaveAndUpdateDto
 * 
 * Description: 平台 权限管理 保存和更新 DTO
 * 
 * @author hozhis
 *
 */
public class PlatformRoleInfoSaveAndUpdateDto extends RequestDto {

    private static final long serialVersionUID = 2271829250636171524L;

    private Integer roleId;

    private String role;

    private String description;

    private List<Integer> functionIds;

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

    public List<Integer> getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(List<Integer> functionIds) {
        this.functionIds = functionIds;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

}
