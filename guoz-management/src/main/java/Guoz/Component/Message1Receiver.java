package Guoz.Component;


import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @Data 14:02
 * @Version 1.0
 **/
@Component
public class Message1Receiver {
    /*@RabbitListener(queues = "test_queue_1")
    @RabbitHandler
    public void receive(String msg){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息接收时间:"+sdf.format(new Date()));
        System.out.println("接收到的消息2:"+msg);
    }

    @RabbitListener(queues = "test_queue_2")
    @RabbitHandler
    public void receive1(String msg){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息接收时间:"+sdf.format(new Date()));
        System.out.println("接收到的消息3:"+msg);
    }*/
}