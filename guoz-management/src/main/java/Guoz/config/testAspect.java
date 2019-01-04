package Guoz.config;

import com.guoz.framework.commons.annotation.testAnnotation;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author 1
 * @version V1.0
 * @since 2018-12-28 15:25
 */
@Aspect
@Component
public class testAspect {
    private static final Logger logger = LoggerFactory.getLogger(testAspect.class);

    @Pointcut("@annotation(com.guoz.framework.commons.annotation.testAnnotation)")
    public void beforeTest(){

    }

    @Around("beforeTest()")
    public Object testSout(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object o;
        String targetName = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Class<?> targetClass = Class.forName(targetName);
        o = proceedingJoinPoint.proceed();
        Method[] methods = targetClass.getMethods();
         for (Method method:methods){
             if (method.getName() == methodName){
                 testAnnotation bl = method.getAnnotation(testAnnotation.class);
                 logger.info(bl.value());
             }

         }

        logger.error(methodName);

        return o;
    }
}
