package com.guoz.framework.commons.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @Data 16:27
 * @Version 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheLock {
    /**
     * redis 锁key的前缀
     *
     * @return
     */
    String prefix() default "";

    /**
     * 过期时间，默认五秒
     *
     * @return
     */
    int expire() default 5;

    /**
     * 设置时间单位
     *
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * Key的分隔符（默认 :）
     * 生成的Key：N:SO1008:500
     *
     * @return
     */
    String delimiter() default ":";
}
