package cn.dolphinsoft.hilife.authority.dto;

import cn.dolphinsoft.hilife.common.dto.RequestDto;

/**
 * Class Name: LoginDto Description: 登陆数据接收Dto
 * 
 * @author hozhis
 *
 */
public class LoginDto extends RequestDto {

    private static final long serialVersionUID = 1171055061212434868L;

    private String loginId;

    private String password;

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
