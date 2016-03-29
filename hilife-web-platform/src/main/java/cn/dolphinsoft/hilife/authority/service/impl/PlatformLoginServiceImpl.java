package cn.dolphinsoft.hilife.authority.service.impl;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformLoginUpdatePwDto;
import cn.dolphinsoft.hilife.authority.service.PlatformLoginService;
import cn.dolphinsoft.hilife.common.constant.BasicTypeConstant;
import cn.dolphinsoft.hilife.common.domain.PlatformUserInfo;
import cn.dolphinsoft.hilife.common.domain.SmsMessage;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.enumeration.BaseStatus;
import cn.dolphinsoft.hilife.common.memcached.constant.CacheType;
import cn.dolphinsoft.hilife.common.repository.BasicParaRepository;
import cn.dolphinsoft.hilife.common.repository.SmsMessageRepository;
import cn.dolphinsoft.hilife.common.repository.PlatformUserInfoRepository;
import cn.dolphinsoft.hilife.common.util.EncryptUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class PlatformLoginServiceImpl implements PlatformLoginService {

    @Autowired
    private PlatformUserInfoRepository platformUserInfoRepository;

    @Autowired
    private BasicParaRepository basicParaRepository;

    @Autowired
    private SmsMessageRepository smsMessageRepository;

    @Resource(name = "freemarkerConfigurer")
    private FreeMarkerConfigurer freeMarkerConfigurer;

    private static final Logger logger = LoggerFactory.getLogger(PlatformLoginServiceImpl.class);

    @Transactional(value = "jpaTransactionManager")
    @Override
    public void clearToken(Integer id) {
        platformUserInfoRepository.clearToken(id);

    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public void saveMessage(String catpcha, Long phone) {
        String message = basicParaRepository.findParaValue1ByTypeId(BasicTypeConstant.USER_SMS_TEMPLATE_GET_CAPTCHA);
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("name", phone);
        dataMap.put("code", catpcha);
        StringWriter out = new StringWriter();
        try {
            new Template("template", new StringReader(message), configuration).process(dataMap, out);
        } catch (Exception e) {
            logger.error("验证码短信模板解析失败...");
            e.printStackTrace();
        }
        message = out.toString();
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setCreateDate(new Date());
        smsMessage.setPhone(phone);
        smsMessage.setStatus(BaseStatus.EFFECT.getKey());
        smsMessage.setMessage(message);
        smsMessageRepository.save(smsMessage);

    }

    @Override
    @Cacheable(value = CacheType.MEMCACHED_FOR_SMS, key = "#phone + '_catpcha'")
    public String getCatpchaByLoginId(Long phone) {
        // 生成四位随机数
        StringBuffer catpchaBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            catpchaBuffer.append(random.nextInt(10));
        }
        return catpchaBuffer.toString();
    }

    @Override
    @Transactional(value = "jpaTransactionManager")
    public ResultDto<String> updatePw(PlatformLoginUpdatePwDto dto) {
        PlatformUserInfo info = platformUserInfoRepository.findByPhone(dto.getPhone(), BaseStatus.EFFECT.getKey());
        if (dto.getPassword() != null) {
            info.setPassword(EncryptUtil.encryptMd5(dto.getPassword(), info.getLoginId()));
            platformUserInfoRepository.save(info);
            return ResultDtoFactory.toAck("修改成功");
        } else {
            return ResultDtoFactory.toNack("修改成功");
        }
    }

}
