package Guoz.config;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO
 *
 * @author 1
 * @version V1.0
 * @since 2019-01-10 15:54
 */
public class DistributeRedisLock {
    protected static Logger logger = LoggerFactory.getLogger(DistributeRedisLock.class);
    private static Redisson redisson = redissonManager.getRedisson();
    private static final String LOCK = "reidsLock_";

    public static boolean acq(String lockName){
        String keyName = LOCK+lockName;

        RLock mulock = redisson.getLock(keyName);
        mulock.lock(2, SECONDS);
        logger.info("lock");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return  true;
    }

    public static boolean rele(String lockName){
        String keyName = LOCK+lockName;
        RLock mulock = redisson.getLock(keyName);
        mulock.unlock();
        logger.info("unlock");
        return  true;
    }
}
