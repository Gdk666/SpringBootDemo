package Guoz.Service;


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

    private static int count = 0;


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

    public static void deadLock() {
        Thread threadC = new Thread(new Runnable() {

            @Override
            public void run(){
                synchronized (UserServiceTest.class){
                    for (int i=0;i<100000;i++){
                        count++;
                    }
                }
            }
        });
        threadC.start();

    }


}