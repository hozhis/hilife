package cn.dolphinsoft.hilife.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.dolphinsoft.hilife.common.authority.domain.RoleFunction;

/**
 * Class Name: PlatformRoleFunction
 * 
 * Description: 平台角色权限关联表
 * 
 * @author hozhis
 *
 */
@Entity
@Table(name = "PLATFORM_ROLE_FUNCTION")
public class PlatformRoleFunction extends RoleFunction {

    private static final long serialVersionUID = -6428280790681762821L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ROLE_ID")
    private Integer roleId;

    @Column(name = "FUNCTION_ID")
    private Integer functionId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

}
