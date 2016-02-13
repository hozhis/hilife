package cn.dolphinsoft.hilife.common.exception;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateOptimisticLockingFailureException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.dolphinsoft.hilife.common.constant.ApplicationConstant;
import cn.dolphinsoft.hilife.common.dto.ResultDto;
import cn.dolphinsoft.hilife.common.dto.ResultDtoFactory;
import cn.dolphinsoft.hilife.common.util.MessageUtil;
import cn.dolphinsoft.hilife.common.util.web.WebUtil;
import cn.dolphinsoft.hilife.common.validation.ValidateException;

/**
 * Class Name: ExceptionResolver
 * <p>
 * Description: the <code>ValidateException</code> handler<br>
 * the validation from service will be wrapped into <code>ValidateException</code>, then the handler will catch the
 * exception and return the errors into view
 * 
 * @author hozhis
 * 
 */
public class ExceptionResolver implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

    @Autowired
    BindingResultExceptionHandler bindingResultExceptionHandler;

    @Autowired
    BeanValidatorExceptionHandler beanValidatorExceptionHandler;

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
     */
    @Override
    public ModelAndView resolveException(final HttpServletRequest request, final HttpServletResponse response,
            final Object handler, final Exception ex) {
        ModelAndView mav = new ModelAndView();
        if (ex instanceof AuthorizationException) {
            LOGGER.info("AuthorizationException handled (non-ajax style):", ex);
            setResult(response, HttpServletResponse.SC_UNAUTHORIZED,
                    ResultDtoFactory.toUnauthorized("访问拒绝【未授权】" + ex.getMessage()));
        } else if (ex instanceof MaxUploadSizeExceededException) {
            LOGGER.debug("MaxUploadSizeExceededException handled (non-ajax style):", ex);
            setResult(response, HttpServletResponse.SC_OK, ResultDtoFactory.toNack("文件大小必须小于2M，请重新上传"));
        } else if (ex instanceof HiLifeException) {
            LOGGER.debug("ZhongCeException handled (non-ajax style):", ex);
            setResult(response, HttpServletResponse.SC_OK, ResultDtoFactory.toBusinessError(ex.getMessage()));
        } else {
            LOGGER.error("Exception handled (non-ajax style):", ex);
            setResult(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, ResultDtoFactory.toNack("服务器错误"));
        }
        return mav;
    }

    private void setResult(HttpServletResponse response, int status, ResultDto<?> error) {
        try (OutputStream os = response.getOutputStream();) {
            response.setStatus(status);
            response.setContentType("application/json;charset=UTF-8");
            ObjectMapper mapper = WebUtil.getObjectMapper();
            String json = mapper.writeValueAsString(error);
            os.write(json.getBytes(ApplicationConstant.ENCODING));
            os.flush();
        } catch (final IOException e) {
            LOGGER.warn("response writer open fail", e);
        }
    }

    /**
     * 
     * Description: return the error message to front-end
     * 
     * @param e
     * @param handler
     * @param formId
     * @param locale
     * @return
     */
    @SuppressWarnings("unused")
    private ResultDto<?> getErrorDto(final Exception ex, final Object handler, final String formId,
            final Locale locale) {
        ResultDto<?> error = new ResultDto<Object>();
        if (ex instanceof ValidateException) {
            return bindingResultExceptionHandler.buildErrorDto(ex, handler, formId);
        } else if (ex instanceof ConstraintViolationException) {
            return beanValidatorExceptionHandler.buildErrorDto(ex, handler, formId);
        } else if (ex instanceof BizServiceException) {
            BizServiceException bizEx = (BizServiceException) ex;
            DisplayableError errorCode = bizEx.getError();
            String msg = MessageUtil.getMessage(errorCode.getDisplayMsg(), locale, errorCode.getArgs());
            if (msg == null) {
                msg = errorCode.getDisplayMsg();
            }
            if (errorCode.isBizError()) {
                error = ResultDtoFactory.toBizError(msg, ex);
            } else {
                error = ResultDtoFactory.toCommonError(bizEx);
            }
            LOGGER.debug("BizServiceException handled:", ex);
        } else if (ex instanceof HiLifeException) {
            error = ResultDtoFactory.toBusinessError(ex.getMessage());
        } else if (ex instanceof MaxUploadSizeExceededException) {
            error = ResultDtoFactory.toNack("文件大小必须小于2M，请重新上传");
        } else if (ex instanceof HibernateOptimisticLockingFailureException) {
            LOGGER.info("HibernateOptimisticLockingFailureException handled:", ex);
            error = ResultDtoFactory.toNack("您正在操作的记录已经在您操作之前被其他用户修改，请刷新页面后重试！");
        } else if (ex instanceof AuthorizationException) {
            LOGGER.warn("AuthorizationException handled:", ex);
            error = ResultDtoFactory.toCommonError(MessageUtil.getMessage("error.common.unauthz"));
        } else {
            error = ResultDtoFactory.toCommonError(ex);
            LOGGER.error("Unknown exception handled:", ex);
        }
        return error;
    }
}
