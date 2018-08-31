package Guoz.Component;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.guoz.framework.commons.annotation.LocalLock;
import com.guoz.framework.commons.utils.common.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @Data 16:01
 * @Version 1.0
 **/
@Aspect
@Configuration
public class LockMethodInterceptor {

    // 利用CacheBuilder.newBuilder() 构建出缓存对象，最大100,过期时间设置为5S
    private static final Cache<Object, Object> CACHES = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(5, TimeUnit.SECONDS).build();
    // 所有的注解LocalLock都被切面 处理
    @Around("execution(public * *(..)) && @annotation(com.guoz.framework.commons.annotation.LocalLock)")
    public Object interceptor(ProceedingJoinPoint proceedingJoinPoint){
        //返回当前连接点签名
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = methodSignature.getMethod();
        //获取LocalLock类
        LocalLock localLock = method.getAnnotation(LocalLock.class);
        String key = getKey(localLock.key(),proceedingJoinPoint.getArgs());
        if (!StringUtils.isEmpty(key)){
            if (CACHES.getIfPresent(key) != null){
                throw new RuntimeException("请勿重复");
            }
            CACHES.put(key,key);
        }
        try {
            //提交来执行目标方法
            return proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException("异常");
        }
    }
    /**
     * key 的生成策略,如果想灵活可以写成接口与实现类的方式
     *
     * @param keyExpress 表达式
     * @param args       参数
     * @return 生成的key
     */
    private String getKey(String keyExpress, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            keyExpress = keyExpress.replace("arg[" + i + "]", args[i].toString());
        }
        return keyExpress;
    }
}
