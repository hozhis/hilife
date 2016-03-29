package cn.dolphinsoft.hilife.authority.service;

import cn.dolphinsoft.hilife.authority.dto.upstream.PlatformLoginUpdatePwDto;
import cn.dolphinsoft.hilife.common.dto.ResultDto;

public interface PlatformLoginService {

    /**
     * 
     * Description: 用户退出清除缓存
     *
     * @param id
     */
    void clearToken(Integer id);

    /**
     * 
     * Description: 忘记密码、更新密码
     *
     * @param dto
     * @return
     */
    ResultDto<String> updatePw(PlatformLoginUpdatePwDto dto);

    /**
     * 
     * Description: 获取验证码 缓存
     *
     * @param phone
     * @return
     */
    String getCatpchaByLoginId(Long phone);

    /**
     * 
     * Description: 将验证码保存至短信表中
     *
     * @param catpcha
     * @param phone
     */
    void saveMessage(String catpcha, Long phone);

}
