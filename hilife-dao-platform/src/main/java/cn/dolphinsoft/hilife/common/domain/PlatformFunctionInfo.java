package cn.dolphinsoft.hilife.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.dolphinsoft.hilife.common.authority.domain.FunctionInfo;

/**
 * Class Name: PlatformFunctionInfo
 * 
 * Description: 平台权限表
 * 
 * @author hozhis
 *
 */
@Entity
@Table(name = "PLATFORM_FUNCTION_INFO")
public class PlatformFunctionInfo extends FunctionInfo {

    private static final long serialVersionUID = 4313948162027417321L;

    @Id
    @Column(name = "FUNCTION_ID")
    private Integer functionId;

    @Column(name = "FUNCTION_NAME")
    private String functionName;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "PRIORITY")
    private Integer priority;

    @Column(name = "PARENT_ID")
    private Integer parentId;

    @Column(name = "PARENT_IDS")
    private String parentIds;

    @Column(name = "PERMISSION")
    private String permission;

    @Column(name = "URL")
    private String url;

    @Column(name = "LEVEL")
    private Integer level;

    public Integer getFunctionId() {
        return functionId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
