package cn.dolphinsoft.hilife.authority.dto.upstream;

import java.io.Serializable;

public class PlatformLoginUpdatePwDto implements Serializable {

    private static final long serialVersionUID = 1736889202305242606L;

    private Long phone;

    private String password;

    private String code;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
