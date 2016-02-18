package cn.dolphinsoft.hilife.common.jpush.util;

import java.util.concurrent.LinkedBlockingQueue;

import cn.dolphinsoft.hilife.common.jpush.bean.JpushBean;

/**
 * Class Name: JpushUtil Description: 推送工具类
 * 
 * @author hozhis
 *
 */
public class JpushUtil {

    private final static LinkedBlockingQueue<JpushBean> jpushBeans = new LinkedBlockingQueue<JpushBean>(50000);

    /**
     * Description: 将需要推送的消息放入推送队列
     *
     * @param jpushBean
     */
    public static void push(JpushBean jpushBean) {
        try {
            getJpushBeans().put(jpushBean);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Description: 从推送队列中取出待推送消息
     *
     * @param jpushBean
     */
    public static JpushBean take() {
        JpushBean jpushBean = null;
        try {
            jpushBean = getJpushBeans().take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jpushBean;
    }

    public static LinkedBlockingQueue<JpushBean> getJpushBeans() {
        return jpushBeans;
    }

}
