package cn.dolphinsoft.hilife.authority.dto;

import cn.dolphinsoft.hilife.common.dto.RequestDto;

/**
 * 
 * Class Name: LoginDto
 * 
 * Description: 登录信息dto
 * 
 * @author hozhis
 *
 */
public class LoginDto extends RequestDto {

    private static final long serialVersionUID = -7019671011624008631L;

    private String loginId;

    private String password;

    private String captcha;

    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
