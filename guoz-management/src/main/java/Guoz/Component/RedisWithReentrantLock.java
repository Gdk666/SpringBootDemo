package Guoz.Component;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:重入锁
 * @Data 15:05
 * @Version 1.0
 * @author: Guoz
 **/
public class RedisWithReentrantLock {
    /*private ThreadLocal<Map<String, Integer>> lockers = new ThreadLocal<>();

    private RedisTemplate redisTemplate;

    public RedisWithReentrantLock(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    private boolean _lock(String key){
        if (key!=null) {
            redisTemplate.opsForValue().set(key, "", 5, TimeUnit.SECONDS);
            return true;
        }else {
            return false;
        }
    }

    private void _unlock(String key){
        redisTemplate.delete(key);
    }

    private Map<String,Integer> currentLockers(){
        Map<String,Integer> refs = lockers.get();
        if (refs != null){
            return refs;
        }
        lockers.set(new HashMap<>());
        return lockers.get();
    }

    public boolean lock(String key){
        Map<String,Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt != null){
            refs.put(key,refCnt + 1);
            return true;
        }
        boolean ok = this._lock(key);
        if (!ok){
            return false;
        }
        refs.put(key,1);
        return true;
    }

    public boolean unlock(String key){
        Map<String,Integer> refs = currentLockers();
        Integer refCnt = refs.get(key);
        if (refCnt == null){
            return false;
        }
        refCnt -= 1;
        if (refCnt > 0){
            refs.put(key,refCnt);
        }else {
            refs.remove(key);
            this._unlock(key);
        }
        return true;
    }

    public static void main(String[] args) {
        RedisTemplate redisTemplate = new RedisTemplate();
        RedisWithReentrantLock redis = new RedisWithReentrantLock(redisTemplate);
        //System.out.println(redis.lock("codehole"));
        //System.out.println(redis.lock("codehole"));
        System.out.println(redis.unlock("codehole"));
        System.out.println(redis.unlock("codehole"));
    }*/
}
