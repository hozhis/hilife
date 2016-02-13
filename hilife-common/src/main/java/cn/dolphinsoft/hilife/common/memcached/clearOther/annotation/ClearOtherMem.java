package cn.dolphinsoft.hilife.common.memcached.clearOther.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.dolphinsoft.hilife.common.memcached.clearOther.constant.SystemType;
import cn.dolphinsoft.hilife.common.memcached.constant.CacheType;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClearOtherMem {

    String type() default SystemType.ALL;

    String value() default CacheType.DEFAULT;

    String[] key() default "";

}
