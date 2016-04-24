package cn.dolphinsoft.hilife.common.enums;

public enum CouponType {
    NEWUSER("1", "新注册"), OTHER("1", "其他");

    private String key;

    private String value;

    private CouponType(String key, String value) {
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
        for (CouponType status : values()) {
            if (status.getKey().equals(key)) {
                return status.getValue();
            }
        }
        return null;

    }

}
