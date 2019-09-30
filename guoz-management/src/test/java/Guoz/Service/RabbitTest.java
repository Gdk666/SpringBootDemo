package Guoz.Service;


import Guoz.config.DistributeRedisLock;
import Guoz.config.RabbitMQ.HelloReceiver;
import Guoz.controller.BaseController;
import Guoz.kafka.consumer.kafkaReceiver;
import Guoz.kafka.provider.kafkaSender;
import Guoz.service.rabbitService.RabbitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//提供虚拟环境随机端口
public class RabbitTest extends BaseController {
    @Autowired
    private RabbitService rabbitService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private kafkaSender kafkaSender;

    @Autowired
    private kafkaReceiver kafkaReceiver;

    @Test
    public void test(){
        String key = "hx123";
        DistributeRedisLock.acq(key);
        //DistributeRedisLock.rele(key);
    }

    @Test
    public void kafkaTest(){
        logger.info("start");
        kafkaSender.send("hello kafka!");
        logger.info("sendEnd");

    }

}