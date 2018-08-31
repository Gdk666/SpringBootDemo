//package Guoz.Component;
//
//
//import Guoz.Service.CacheKeyGenerator;
//import com.guoz.framework.commons.annotation.CacheLock;
//import com.guoz.framework.commons.utils.common.RedisLockHelper;
//import com.guoz.framework.commons.utils.common.StringUtils;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//import java.lang.reflect.Method;
//import java.util.UUID;
//
///**
// * @description:
// * @Data 16:53
// * @Version 1.0
// **/
//@Aspect
//@Configuration
//public class LockMethodInterceptor2 {
//    @Autowired
//    public LockMethodInterceptor2(RedisLockHelper redisLockHelper, CacheKeyGenerator cacheKeyGenerator) {
//        this.redisLockHelper = redisLockHelper;
//        this.cacheKeyGenerator = cacheKeyGenerator;
//    }
//    private final RedisLockHelper redisLockHelper;
//    private final CacheKeyGenerator cacheKeyGenerator;
//
//    @Around("execution(public * *(..)) && @annotation(com.guoz.framework.commons.annotation.CacheLock)")
//    public Object interceptor(ProceedingJoinPoint pjp) {
//        //获取注释类所切的签名点
//        MethodSignature signature = (MethodSignature) pjp.getSignature();
//        //获取方法名
//        Method method = signature.getMethod();
//        //CacheLock的参数
//        CacheLock lock = method.getAnnotation(CacheLock.class);
//        if (StringUtils.isEmpty(lock.prefix())) {
//            throw new RuntimeException("lock key don't null...");
//        }
//        //拼接lockKey名称
//        final String lockKey = cacheKeyGenerator.getLockKey(pjp);
//        String value = UUID.randomUUID().toString();
//        try {
//            // 假设上锁成功，但是设置过期时间失效，以后拿到的都是 false
//            final boolean success = redisLockHelper.lock(lockKey, value, lock.expire(), lock.timeUnit());
//            if (!success) {
//                throw new RuntimeException("重复提交");
//            }
//            try {
//                //提交
//                return pjp.proceed();
//            } catch (Throwable throwable) {
//                throw new RuntimeException("系统异常");
//            }
//        } finally {
//            // TODO 如果演示的话需要注释该代码;实际应该放开
//            //redisLockHelper.unlock(lockKey, value);
//            System.out.println(lockKey);
//        }
//    }
//
//}
