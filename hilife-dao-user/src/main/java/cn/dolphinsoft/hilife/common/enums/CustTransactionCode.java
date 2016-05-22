package cn.dolphinsoft.hilife.common.enums;

public enum CustTransactionCode {
    PAY_SUCCESS(101, "支付成功"), PAY_ERROR(102, "支付失败");

    private Integer key;

    private String value;

    private CustTransactionCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static String getValueByKey(String key) {
        for (CustTransactionCode status : values()) {
            if (status.getKey().equals(key)) {
                return status.getValue();
            }
        }
        return null;
    }
}
