package cn.dolphinsoft.hilife.common.handler;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.dolphinsoft.hilife.common.authority.AuthorityContext;
import cn.dolphinsoft.hilife.common.util.AppConfigUtil;
import cn.dolphinsoft.hilife.common.util.web.WebUtil;

/**
 * Class Name: ResultHandler Description: TODO
 * 
 * @author hozhis
 *
 */
@Aspect
@Component
public class ResultHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultHandler.class);

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object handlerRequestMapping(final ProceedingJoinPoint joinPoint) throws Throwable {
        // 初始化参数
        String token = AuthorityContext.getCurrentToken();
        if (token != null) {
            LOGGER.debug("设置当前用户缓存");
            AuthorityContext.setCurrentUser();
        }
        final Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 如果返回为页面，则为页面添加参数
        if (String.class.equals(method.getReturnType())) {
            LOGGER.debug("设置页面参数");
            HttpServletRequest request = WebUtil.getThreadRequest();
            request.setAttribute("token", token);
            request.setAttribute("menuIndex", AuthorityContext.getMenuIndex(getMappingUrl(method)));
            request.setAttribute("auth", new AuthorityContext());
            request.setAttribute("staticPath", AuthorityContext.getStaticPath());
            if (AppConfigUtil.isDevEnv()) {
                LOGGER.debug("开发环境，静态资源为本机");
                request.setAttribute("contextPath", request.getContextPath());
            } else {
                LOGGER.debug("非开发环境，静态资源为静态资源服务器");
                request.setAttribute("contextPath", AuthorityContext.getContextPath());
            }
        }
        // 执行方法
        return joinPoint.proceed();
    }

    private String getMappingUrl(Method method) {
        String url = "";
        RequestMapping clazzMapping = method.getDeclaringClass().getAnnotation(RequestMapping.class);
        RequestMapping methodMapping = method.getAnnotation(RequestMapping.class);
        if (clazzMapping != null) {
            url += clazzMapping.value()[0];
        }
        if (methodMapping != null) {
            url += methodMapping.value()[0];
        }
        return url;
    }
}
