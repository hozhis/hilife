package cn.dolphinsoft.hilife.common.authority.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Class Name: StatelessToken Description: 无状态Token
 * 
 * @author hozhis
 *
 */
public class StatelessToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private String token;

    private String url;

    public StatelessToken(String token) {
        this.token = token;
    }

    public StatelessToken(String token, String url) {
        this.token = token;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
