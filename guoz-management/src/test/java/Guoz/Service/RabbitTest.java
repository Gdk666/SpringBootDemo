package Guoz.Service;


import Guoz.config.RabbitMQ.HelloReceiver;
import Guoz.controller.BaseController;
import Guoz.service.rabbitService.RabbitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)//提供虚拟环境随机端口
public class RabbitTest extends BaseController {
    @Autowired
    private RabbitService rabbitService;

    @Test
    public void test(){
        rabbitService.defaultMessage();
    }

}