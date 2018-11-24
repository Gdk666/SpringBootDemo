package Guoz.config.RabbitMQ;

import Guoz.pojo.po.Manager;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HelloReceiver {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = "test_queue_2")
    public void process(Manager manager, Message message, Channel channel) {
        // TODO 如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            logger.info("Receiver  : " + manager.getEmail());
            //消费已经成功可以ACK
            channel.basicAck(deliveryTag,false);
        } catch (IOException e) {
            try {
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = {QueueConfig.REGISTER_QUEUE_NAME})
    public void process1(Manager manager, Message message, Channel channel) {
        // TODO 如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            logger.info("Receiver  : " + manager.getEmail());
            //消费已经成功可以ACK
            channel.basicAck(deliveryTag,false);
            System.out.println("123");
        } catch (IOException e) {
            try {
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
 
}