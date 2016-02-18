package cn.dolphinsoft.hilife.common.jpush.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.dolphinsoft.hilife.common.enumeration.AppTypeEnum;
import cn.dolphinsoft.hilife.common.enumeration.MessageReadStatus;
import cn.dolphinsoft.hilife.common.enumeration.MessageType;
import cn.dolphinsoft.hilife.common.jpush.bean.JpushBean;
import cn.dolphinsoft.hilife.common.jpush.dao.IMessageDao;
import cn.dolphinsoft.hilife.common.jpush.entity.MessageEntity;
import cn.dolphinsoft.hilife.common.jpush.util.JpushUtil;
import cn.dolphinsoft.hilife.common.util.AppConfigUtil;
import cn.dolphinsoft.hilife.common.util.HiLifeUtil;
import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

/**
 * Class Name: JpushService Description: 推送服务类
 * 
 * @author hozhis
 *
 */
public class JpushService implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(JpushService.class);

    private static final String IOS_DEFAULT_SOUND = "default";

    private static final String JPUSH_APPKEY_USER = "Jpush.User.AppKey";

    private static final String JPUSH_MASTERSECRET_USER = "Jpush.User.MasterSecret";

    private static final String JPUSH_APPKEY_STORE = "Jpush.Store.AppKey";

    private static final String JPUSH_MASTERSECRET_STORE = "Jpush.Store.MasterSecret";

    private static final String JPUSH_APNS_PRODUCTION = "Jpush.Apns.Production";

    private static JPushClient jpushUserClient;

    private static JPushClient jpushStoreClient;

    private boolean jpuhSwitch = false;

    @Autowired
    private IMessageDao messageDao;

    public void setJpuhSwitch(boolean jpuhSwitch) {
        this.jpuhSwitch = jpuhSwitch;
    }

    /**
     * 描述: 初始化时启动jpush线程
     * 
     * @param
     * @return
     */
    @PostConstruct
    public void init() {
        if (jpuhSwitch) {
            Thread jpushThread = new Thread(this);
            jpushThread.setDaemon(true);
            jpushThread.start();
        }
    }

    @Override
    public void run() {
        while (true) {
            JpushBean jpushBean = JpushUtil.take();
            if (jpushBean != null) {
                // 根据不同类型向不同的端发送消息
                JPushClient jPushClient = null;
                if (AppTypeEnum.CUSTOMER.getKey().equals(jpushBean.getAppType())) {
                    jPushClient = getCustomerJPushClient();
                } else if (AppTypeEnum.STORE.getKey().equals(jpushBean.getAppType())) {
                    jPushClient = getStoreJPushClient();
                } else {
                    continue;
                }
                // 设置不同的发送对象
                PushPayload pushPayload = null;
                if (!jpushBean.getAliases().isEmpty()) {
                    pushPayload = toAllPlatformNotificationByRegistrationId(jpushBean);
                } else if (!jpushBean.getTagValues().isEmpty()) {
                    pushPayload = toAllPlatformNotificationByTag(jpushBean);
                } else {
                    continue;
                }
                // 发送消息
                try {
                    jPushClient.sendPush(pushPayload);
                    LOGGER.info("Jpushs推送成功" + jpushBean);
                    saveMessage(jpushBean);
                    LOGGER.info("消息保存成功");
                } catch (APIConnectionException e) {
                    LOGGER.error("Jpush连接错误", e);
                } catch (APIRequestException e) {
                    LOGGER.error("JPush推送失败", e);
                    LOGGER.debug("HTTP Status" + e.getStatus());
                    LOGGER.debug("Error Code" + e.getErrorCode());
                    LOGGER.debug("Error Message" + e.getErrorMessage());
                } catch (Exception e) {
                    LOGGER.error("消息发送失败", e);
                }
            }
        }
    }

    /**
     * Description: 保存消息至数据库
     *
     * @param jpushBean
     */
    private void saveMessage(JpushBean jpushBean) {
        String param = HiLifeUtil.parseObjTOString(jpushBean.getExtras());
        Date date = new Date();
        for (Integer toId : jpushBean.getToIds()) {
            MessageEntity entity = new MessageEntity();
            entity.setFromId(jpushBean.getFormId());
            entity.setToId(toId);
            entity.setFromType(jpushBean.getFromType());
            entity.setToType(jpushBean.getToType());
            entity.setParam(param);
            entity.setContent(jpushBean.getAlert());
            entity.setType(MessageType.JPUSH.getKey());
            entity.setReadStatus(MessageReadStatus.UNREAD.getKey());
            entity.setCreatDate(date);
            messageDao.save(entity);
        }
    }

    /**
     * Description: 获得用户端JpushClient
     *
     * @return
     */
    private JPushClient getCustomerJPushClient() {
        if (jpushUserClient == null) {
            synchronized (JpushService.class) {
                if (jpushUserClient == null) {
                    jpushUserClient = new JPushClient(AppConfigUtil.getConfig(JPUSH_MASTERSECRET_USER),
                            AppConfigUtil.getConfig(JPUSH_APPKEY_USER));
                }
            }
        }
        return jpushUserClient;
    }

    /**
     * Description: 获得门店端JpushClient
     *
     * @return
     */
    private JPushClient getStoreJPushClient() {
        if (jpushStoreClient == null) {
            synchronized (JpushService.class) {
                if (jpushStoreClient == null) {
                    jpushStoreClient = new JPushClient(AppConfigUtil.getConfig(JPUSH_MASTERSECRET_STORE),
                            AppConfigUtil.getConfig(JPUSH_APPKEY_STORE));
                }
            }
        }
        return jpushStoreClient;
    }

    /**
     * Description:构建消息内容
     *
     * @param platform
     * @param audience
     * @param notification
     * @param message
     * @param options
     * @return
     */
    private PushPayload buildPushObject(Platform platform, Audience audience, Notification notification,
            Message message, Options options) {
        return PushPayload.newBuilder().setPlatform(platform).setAudience(audience).setNotification(notification)
                .setMessage(message).setOptions(options).build();
    }

    /**
     * Description: 向全平台根据用户ID表示发送通知
     *
     * @return
     */
    private PushPayload toAllPlatformNotificationByRegistrationId(JpushBean jpushBean) {
        Platform platform = Platform.all();
        Audience audience = Audience.alias(jpushBean.getAliases());
        Notification notification = Notification.newBuilder().setAlert(jpushBean.getAlert())
                .addPlatformNotification(IosNotification.newBuilder().setSound(IOS_DEFAULT_SOUND)
                        .addExtras(jpushBean.getExtras()).build())
                .addPlatformNotification(AndroidNotification.newBuilder().addExtras(jpushBean.getExtras()).build())
                .build();
        Options options = Options.newBuilder()
                .setApnsProduction(Boolean.valueOf(AppConfigUtil.getConfig(JPUSH_APNS_PRODUCTION))).build();
        return buildPushObject(platform, audience, notification, null, options);
    }

    /**
     * Description: 向全平台根据标签表示发送通知
     *
     * @return
     */
    private PushPayload toAllPlatformNotificationByTag(JpushBean jpushBean) {
        Platform platform = Platform.all();
        Audience audience = Audience.tag(jpushBean.getTagValues());
        Notification notification = Notification.newBuilder().setAlert(jpushBean.getAlert())
                .addPlatformNotification(IosNotification.newBuilder().setSound(IOS_DEFAULT_SOUND)
                        .addExtras(jpushBean.getExtras()).build())
                .addPlatformNotification(AndroidNotification.newBuilder().addExtras(jpushBean.getExtras()).build())
                .build();
        Options options = Options.newBuilder()
                .setApnsProduction(Boolean.valueOf(AppConfigUtil.getConfig(JPUSH_APNS_PRODUCTION))).build();
        return buildPushObject(platform, audience, notification, null, options);
    }
}
