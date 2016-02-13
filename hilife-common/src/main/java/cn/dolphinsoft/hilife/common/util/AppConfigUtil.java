package cn.dolphinsoft.hilife.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;

import org.springframework.context.MessageSource;

/**
 * Class Name: AppConfigUtil
 * 
 * Description: 获取应用全局配置
 * 
 * @author hozhis
 * 
 */
public final class AppConfigUtil {

    private static final String ENVIRONMENT = "env";

    private static MessageSource messageSource;

    public static String getConfig(String key) {
        return messageSource.getMessage(key, null, Locale.ROOT);
    }

    /**
     * Whether current profile is for PROD environment.
     * 
     * @return
     */
    public static boolean isProdEnv() {
        return "PROD".equalsIgnoreCase(getConfig(ENVIRONMENT));
    }

    /**
     * Whether current profile is for DEV environment.
     * 
     * @return
     */
    public static boolean isDevEnv() {
        return "DEV".equalsIgnoreCase(getConfig(ENVIRONMENT));
    }

    /**
     * @param messageSource
     *            Set messageSource value
     */
    public static void setMessageSource(MessageSource messageSource) {
        AppConfigUtil.messageSource = messageSource;
    }

    public static String getLocalAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new IllegalAccessError(e.getMessage());
        }
    }

}
