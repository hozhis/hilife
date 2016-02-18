package cn.dolphinsoft.hilife.common.memcached.clearOther;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import cn.dolphinsoft.hilife.common.memcached.clearOther.annotation.ClearOtherMem;
import cn.dolphinsoft.hilife.common.memcached.clearOther.constant.SystemType;

@Aspect
@Component
public class ClearOtherMemAspect {

    @Autowired
    private OtherMemClear otherMemClear;

    @AfterReturning("@annotation(com.zcckj.common.memcahced.clearOther.annotation.ClearOtherMem)")
    public void clearOtherMem(final JoinPoint joinPoint) {
        final Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        ClearOtherMem clearOtherMem = method.getAnnotation(ClearOtherMem.class);
        Object[] args = joinPoint.getArgs();
        for (String key : clearOtherMem.key()) {
            // SPEL表达式解析
            key = this.SpelExpressionParser(method, args, key);
            if (SystemType.ALL.equals(clearOtherMem.type())) {
                otherMemClear.clear(SystemType.USER, clearOtherMem.value(), key);
                otherMemClear.clear(SystemType.SUPPLIER, clearOtherMem.value(), key);
                otherMemClear.clear(SystemType.SHOP, clearOtherMem.value(), key);
                otherMemClear.clear(SystemType.PLATFORM, clearOtherMem.value(), key);
            } else {
                otherMemClear.clear(clearOtherMem.type(), clearOtherMem.value(), key);
            }
        }
    }

    /**
     * Description: SPEL解析
     *
     * @param method
     * @param args
     * @param key
     * @return
     */
    private String SpelExpressionParser(Method method, Object[] args, String key) {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext();
        if (!ObjectUtils.isEmpty(args)) {
            String[] parameterNames = new DefaultParameterNameDiscoverer().getParameterNames(method);
            if (parameterNames != null) {
                for (int i = 0; i < parameterNames.length; i++) {
                    context.setVariable(parameterNames[i], args[i]);
                }
            }
        }
        key = parser.parseExpression(key).getValue(context, String.class);
        return key;
    }
}
