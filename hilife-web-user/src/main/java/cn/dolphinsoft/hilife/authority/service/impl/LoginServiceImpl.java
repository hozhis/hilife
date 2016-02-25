package cn.dolphinsoft.hilife.authority.service.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.dolphinsoft.hilife.common.constant.BasicTypeConstant;
import cn.dolphinsoft.hilife.common.domain.BasicPara;
import cn.dolphinsoft.hilife.common.domain.SmsMessage;
import cn.dolphinsoft.hilife.common.repository.ICustUserInfoRepository;
import cn.dolphinsoft.hilife.common.repository.ISmsMessageRepository;
import cn.dolphinsoft.hilife.common.service.CacheService;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * Class Name: LoginServiceImpl Description: 登陆业务实现
 * 
 * @author hozhis
 *
 */
@Service
public class LoginServiceImpl implements cn.dolphinsoft.hilife.authority.service.LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Resource(name = "freemarkerConfigurer")
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private ICustUserInfoRepository custUserInfoRepository;

    @Autowired
    private ISmsMessageRepository smsMessageRepository;

    @Autowired
    private CacheService cacheService;

    @Override
    public String sendLoginSms(String loginId) {
        // TODO 随机获取
        String code = "123456";
        // 模板数据库取
        List<BasicPara> list = cacheService.findBasicParaByTypeId(BasicTypeConstant.USER_SMS_TEMPLATE_GET_CAPTCHA);
        String template = "${name}您的验证码为${code}，该验证码将在15分钟后过期。";
        if (!list.isEmpty()) {
            template = list.get(0).getParaValue1();
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name", loginId);
        dataMap.put("code", code);
        String message = freeMarkerTemplate(template, dataMap);
        // 存短信表
        SmsMessage entity = new SmsMessage();
        entity.setCreateDate(new Date());
        entity.setMessage(message);
        entity.setPhone(Long.valueOf(loginId));
        smsMessageRepository.save(entity);
        return code;
    }

    /**
     * Description: freeMarker模板解析
     *
     * @param message
     * @param dataMap
     * @return
     */
    public String freeMarkerTemplate(String message, Map<String, Object> dataMap) {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        StringWriter out = new StringWriter();
        try {
            new Template("template", new StringReader(message), configuration).process(dataMap, out);
        } catch (Exception e) {
            logger.error("模板解析失败...");
            e.printStackTrace();
        }
        message = out.toString();
        return message;
    }

    @Override
    public String getLoginSms(String loginId) {
        return null;
    }

    @Override
    public void clearLoginSms(String loginId) {
    }

    @Override
    public void clearToken(String token) {
        custUserInfoRepository.clearToken(token);
    }

}
