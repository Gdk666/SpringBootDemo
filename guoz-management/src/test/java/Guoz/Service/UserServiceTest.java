package Guoz.Service;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Guoz.controller.BaseController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StopWatch;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//提供虚拟环境随机端口

public class UserServiceTest extends BaseController {

    private static String resource_a = "A";

    private static String resource_b = "B";

    private static  int count = 0;

    private static final Lock lock = new ReentrantLock();

    private static Condition condition = lock.newCondition();




    @Test
    public void test() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0;i<10;i++){
            deadLock();
        }

        stopWatch.stop();
        logger.info("count数目:{} 运行时间:{}",count,stopWatch.getTotalTimeMillis());
    }

    public static  synchronized void inc(){
        count++;
    }

    public static void deadLock() {
        Thread threadC = new Thread(new Runnable() {
            @Override
            public  void run(){
                    for (int i=0;i<100000;i++){
                        try {
                            Thread.sleep(1000);
                            inc();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }

            }
        });
        threadC.start();

    }


}