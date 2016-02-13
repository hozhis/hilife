package cn.dolphinsoft.hilife.common.enumeration;

public enum AppTypeEnum {
    CUSTOMER("0", "客户端"), STORE("1", "门店端");

    private String key;

    private String value;

    private AppTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValue(String key) {
        for (AppTypeEnum status : values()) {
            if (status.getKey().equals(key)) {
                return status.getValue();
            }
        }
        return null;

    }

}
