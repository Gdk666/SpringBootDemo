package com.guoz.framework.commons.annotation;

import java.lang.annotation.*;

/**
 * @description:
 * @Data 16:32
 * @Version 1.0
 **/
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {
    /**
     * 字段名称
     * @return
     */
    String name() default "";


}
