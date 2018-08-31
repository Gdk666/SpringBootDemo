package Guoz.Component;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @description:
 * @Data 14:17
 * @Version 1.0
 **/
@Component
@Slf4j
public class SpringTaskDemo {
    private static Logger LOGGER = LoggerFactory.getLogger(SpringTaskDemo.class);

   /* @Async
    @Scheduled(cron = "0/1 * * * * *")
    public void scheduled1() throws InterruptedException{
        Thread.sleep(3000);
        log.debug("scheduled1 每1秒执行一次：{}", LocalDateTime.now());
        System.out.println("scheduled1 每1秒执行一次："+LocalDateTime.now());
    }
    @Scheduled(fixedRate = 1000)
    public void scheduled2() throws InterruptedException{
        Thread.sleep(3000);
        System.out.println("scheduled2 每1秒执行一次："+LocalDateTime.now());
    }*/

    /*@Scheduled(fixedDelay = 3000)
    public void scheduled3() throws InterruptedException{
        Thread.sleep(3000);
        System.out.println("scheduled3 每3秒执行一次："+ LocalDateTime.now());
    }*/
}
