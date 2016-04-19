package cn.dolphinsoft.hilife.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Name: BasicTypeConstant
 * 
 * Description: TODO 基础数据表 basic_type 常量设置
 * 
 * @author hozhis
 *
 */
public final class BasicTypeConstant {

    private BasicTypeConstant() {
    }

    public static final String USER_APP_VERSION = "1";

    public static final Integer USER_APP_VERSION_ANDROID = 1;

    public static final Integer USER_APP_VERSION_IOS = 2;

    public static final String AUNT_APP_VERSION = "2";

    public static final Integer AUNT_APP_VERSION_ANDROID = 3;

    public static final Integer AUNT_APP_VERSION_IOS = 4;

    public static final String USER_SETTINGS = "3";

    public static final Integer USER_SERVICE_HOTLINE = 7;

    public static final String AUNT_SETTINGS = "4";

    public static final Integer AUNT_SERVICE_RANGE = 8;

    public static final String USER_SMS_TEMPLATE = "5";

    public static final Integer USER_SMS_TEMPLATE_GET_CAPTCHA = 15;

    public static final Integer PLATFORM_SMS_TEMPLATE_GET_CAPTCHA = 16;

    public static final Integer AUNT_SMS_TEMPLATE_GET_CAPTCHA = 17;

    public static final Integer SUBMIT_ORDER_SMS_TEMPLATE_GET_CAPTCHA = 18;

    public static final Integer AUNT_ACCEPT_SMS_TEMPLATE_GET_CAPTCHA = 19;

    public static final Integer ORDER_FINISHED_SMS_TEMPLATE_GET_CAPTCHA = 20;

    public static final String PLATFORM_SETTINGS = "6";

    public static final Integer FTP_SETTING_PLATFORM = 30;

    public static final Integer PLATFORM_SETTING_PLATFORM = 31;

    public static final Integer USER_SETTING_PLATFORM = 32;

    public static final Integer AUNT_SETTING_PLATFORM = 33;

    public static List<Integer> getList(String type) {
        List<Integer> list = new ArrayList<Integer>();

        if (USER_APP_VERSION.equals(type)) {
            list.add(USER_APP_VERSION_ANDROID);
            list.add(USER_APP_VERSION_IOS);
        }

        else if (AUNT_APP_VERSION.equals(type)) {
            list.add(AUNT_APP_VERSION_ANDROID);
            list.add(AUNT_APP_VERSION_IOS);
        }

        else if (USER_SETTINGS.equals(type)) {
            list.add(USER_SERVICE_HOTLINE);
        }

        else if (AUNT_SETTINGS.equals(type)) {
            list.add(AUNT_SERVICE_RANGE);
        }

        else if (USER_SMS_TEMPLATE.equals(type)) {
            list.add(USER_SMS_TEMPLATE_GET_CAPTCHA);
            list.add(AUNT_SMS_TEMPLATE_GET_CAPTCHA);
            list.add(PLATFORM_SMS_TEMPLATE_GET_CAPTCHA);
            list.add(SUBMIT_ORDER_SMS_TEMPLATE_GET_CAPTCHA);
            list.add(AUNT_ACCEPT_SMS_TEMPLATE_GET_CAPTCHA);
            list.add(ORDER_FINISHED_SMS_TEMPLATE_GET_CAPTCHA);
        }

        else if (PLATFORM_SETTINGS.equals(type)) {
            list.add(FTP_SETTING_PLATFORM);
            list.add(PLATFORM_SETTING_PLATFORM);
            list.add(USER_SETTING_PLATFORM);
            list.add(AUNT_SETTING_PLATFORM);
        }

        return list;
    }
}
