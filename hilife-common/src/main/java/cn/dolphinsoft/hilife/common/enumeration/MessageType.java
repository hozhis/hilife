package cn.dolphinsoft.hilife.common.enumeration;

public enum MessageType {

    JPUSH("1", "推送消息"), TEXT("2", "文本消息");

    private String key;

    private String value;

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    private MessageType(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
