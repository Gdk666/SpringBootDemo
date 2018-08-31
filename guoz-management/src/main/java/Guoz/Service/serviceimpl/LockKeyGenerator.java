package Guoz.Service.serviceimpl;


import Guoz.Service.CacheKeyGenerator;
import com.guoz.framework.commons.annotation.CacheLock;
import com.guoz.framework.commons.annotation.CacheParam;
import com.guoz.framework.commons.utils.common.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @description:
 * @Data 16:36
 * @Version 1.0
 **/
public class LockKeyGenerator implements CacheKeyGenerator {
    @Override
    public String getLockKey(ProceedingJoinPoint prj) {
        MethodSignature signature = (MethodSignature) prj.getSignature();
        Method method = signature.getMethod();
        CacheLock lock = method.getAnnotation(CacheLock.class);
        final Object[] args = prj.getArgs();
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i < parameters.length;i++){
            final CacheParam annotation = parameters[i].getAnnotation(CacheParam.class);
            if (annotation == null){
                continue;
            }
            builder.append(lock.delimiter()).append(args[i]);
        }
        if (StringUtils.isEmpty(builder.toString())){
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0;i < parameterAnnotations.length;i++){
                final Object object = args[i];
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field:fields){
                    final CacheParam annotation = field.getAnnotation(CacheParam.class);
                    if (annotation == null){
                        continue;
                    }
                    field.setAccessible(true);
                    builder.append(lock.delimiter()).append(ReflectionUtils.getField(field,object));

                }
            }
        }
        return lock.prefix()+builder.toString();
    }
}
