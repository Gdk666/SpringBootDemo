package Guoz.Service;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @description:
 * @Data 16:34
 * @Version 1.0
 **/

public interface CacheKeyGenerator {
    /**
     * 获取AOP参数,生成指定缓存Key
     * @param prj
     * @return
     */
    String getLockKey(ProceedingJoinPoint prj);
}
