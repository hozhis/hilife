package cn.dolphinsoft.hilife.common.authority.shiro;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.dolphinsoft.hilife.common.authority.BodyReaderHttpServletRequestWrapper;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.util.web.WebUtil;

/**
 * Class Name: StatelessAuthcFilter Description: token验证Filter
 * 
 * @author hozhis
 *
 */
public class StatelessAuthcFilter extends AccessControlFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(StatelessAuthcFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        String token = request.getParameter("token");
        if (token == null && (Boolean) request.getAttribute(BodyReaderHttpServletRequestWrapper.BODY_NONEMPTY)) {
            Map<String, Object> map = WebUtil.getObjectMapper().readValue(request.getInputStream(), Map.class);
            token = (String) map.get("token");
        }
        if (token != null) {
            StatelessToken statelessToken = new StatelessToken(token);
            try {
                getSubject(request, response).login(statelessToken);
                return true;
            } catch (Exception e) {
                LOGGER.info("Token:{}无效， 验证失败", token);
            }
        }
        if (isAjaxRequest((HttpServletRequest) request)) {
            onLoginFail(response);
        } else {
            WebUtils.issueRedirect(request, response, getLoginUrl());
        }
        return false;
    }

    // 登录失败时默认返回401状态码
    private void onLoginFail(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ObjectMapper mapper = WebUtil.getObjectMapper();
        String json = mapper.writeValueAsString(ResultDtoFactory.toUnauthorized("访问拒绝【未授权】"));
        LOGGER.info("权限验证失败");
        httpResponse.getOutputStream().write(json.getBytes("UTF-8"));
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        String requestedWith = request.getHeader("X-Requested-With");
        return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
    }
}
