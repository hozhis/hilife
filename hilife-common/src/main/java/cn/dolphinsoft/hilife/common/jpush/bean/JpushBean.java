package cn.dolphinsoft.hilife.common.jpush.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JpushBean {

    private String appType; // app类型

    private Set<String> aliases = new HashSet<String>(); // 别名标记

    private Set<String> tagValues = new HashSet<String>(); // 标签标记

    private Map<String, String> extras = new HashMap<String, String>(); // 推送的数据信息,用于前后台交互

    private String alert; // 推送消息的内容

    private Integer formId; // 发送方ID

    private Set<Integer> toIds = new HashSet<Integer>(); // 接收方ID

    private String fromType; // 发送方类型

    private String toType; // 接收方类型

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public Map<String, String> getExtras() {
        return extras;
    }

    public void setExtras(Map<String, String> extras) {
        this.extras = extras;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public Set<String> getAliases() {
        return aliases;
    }

    public void setAliases(Set<String> aliases) {
        this.aliases = aliases;
    }

    public Set<String> getTagValues() {
        return tagValues;
    }

    public void setTagValues(Set<String> tagValues) {
        this.tagValues = tagValues;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public Set<Integer> getToIds() {
        return toIds;
    }

    public void setToIds(Set<Integer> toIds) {
        this.toIds = toIds;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public String getToType() {
        return toType;
    }

    public void setToType(String toType) {
        this.toType = toType;
    }

}
