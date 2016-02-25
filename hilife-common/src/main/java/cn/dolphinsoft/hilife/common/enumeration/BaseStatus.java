package cn.dolphinsoft.hilife.common.enumeration;

/**
 * Class Name: BaseStatus Description: 默认STATUS的状态枚举
 * 
 * @author hozhis
 */
public enum BaseStatus {

    INVALID("0", "失效"), EFFECT("1", "生效");
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

    /**
     * @param key
     * @param value
     */
    private BaseStatus(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
