package cn.dolphinsoft.hilife.common.enumeration;

public enum MessageSenderType {

    PLATFORM_MESSAGE("1", "平台"), SUPPLIER_MESSAGE("2", "经销商"), STORE_MESSAGE("3", "门店"), USER_MESSAGE("4", "用户");

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

    private MessageSenderType(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
