package cn.dolphinsoft.hilife.common.enumeration;

/**
 * Class Name: UserStatusEnum
 * 
 * Description: 用户管理 枚举 0--正常 1--注销
 * 
 * @author hozhis
 *
 */
public enum UserInfoStatusEnum {

    NORMAL("1", "正常"), CANCEL("0", "注销");

    private String key;

    private String value;

    private UserInfoStatusEnum(String key, String value) {
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

    public static String getKeyByValue(String value) {
        for (UserInfoStatusEnum temp : values()) {
            if (temp.getValue().equals(value)) {
                return temp.getKey();
            }
        }
        return null;
    }

    public static String getValueByKey(String key) {
        for (UserInfoStatusEnum temp : values()) {
            if (temp.getKey().equals(key)) {
                return temp.getValue();
            }
        }
        return null;
    }
}
