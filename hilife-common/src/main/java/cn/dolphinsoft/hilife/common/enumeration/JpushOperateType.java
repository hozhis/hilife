package cn.dolphinsoft.hilife.common.enumeration;

/**
 * Class Name: JpushOperateType Description: TODO jpush推送消息类别枚举
 * 
 * @author hozhis
 *
 */
public enum JpushOperateType {
    JPUSH_OPERATE_TYPE("0", "jpushOperateType"), // jpushbean中业务参数extras(前后台交互数据)传递的属性名
                                                 // jpushOperateType

    // 1开头发送给门店 2开头发送给客户
    USER_START_SERVICE("101", "用户发起救援"), USER_CANCEL_ORDER("102", "用户取消订单"),

    STORE_ACCEPT_ORDER("201", "门店接单"), STORE_CANCEL_ORDER("203", "门店取消订单"), STORE_CAR_SUBMIT("204",
            "已发车"), STORE_CAR_ARREIVED("205", "车辆到达"), STORE_COMPLETE_SERVICE("206", "服务完成"),

    RE_ASSIGN("207", "重新分配"), NO_STROE("208", "无门店接单");

    private String key;

    private String value;

    /**
     * JpushOperateType Constructor
     *
     * @param key
     * @param value
     */
    private JpushOperateType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @return return the value of the var key
     */
    public String getKey() {
        return key;
    }

    /**
     * Description: Set key value
     * 
     * @param key
     * 
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return return the value of the var value
     */
    public String getValue() {
        return value;
    }

    /**
     * Description: Set value value
     * 
     * @param value
     * 
     */
    public void setValue(String value) {
        this.value = value;
    }

}
