package com.guoz.framework.commons.annotation;

import java.lang.annotation.*;

/**
 * @description:
 * @Data 15:50
 * @Version 1.0
 **/
@Target(ElementType.METHOD) //表示这个Annotation存储在class中，可以由虚拟机读取,反射需要
@Retention(RetentionPolicy.RUNTIME)//表示这个Annotation存入class但vm不读取
@Documented  //这个Annotation可以被写入javadoc
@Inherited //这个Annotation 可以被继承
public @interface LocalLock {
    String key() default "";

    int expire() default 5;


}
