package Guoz.service.impl;/**
 * @Auther: Guoz666
 * @Date: 2018/11/9 15:25
 * @Description:
 */

import Guoz.config.RabbitMQ.QueueConfig;
import Guoz.pojo.po.Log;
import Guoz.pojo.po.Manager;
import Guoz.service.rabbitService.RabbitService;
import com.guoz.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.AbstractJavaTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.time.LocalDateTime;

/**
 *@description:
 *@Data 15:25
 *@Version 1.0
 *@author: Guoz
 **/
@Service
public class RabbitServiceImpl extends BaseServiceImpl<Log> implements RabbitService {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void defaultMessage() {
        String str = "763366136@qq.com";
        Manager manager = new Manager();
        manager.setEmail(str);
        // 添加延时队列
        this.rabbitTemplate.convertAndSend(QueueConfig.REGISTER_DELAY_EXCHANGE, QueueConfig.DELAY_ROUTING_KEY, manager, message -> {
            // TODO 第一句是可要可不要,根据自己需要自行处理
            message.getMessageProperties().setHeader(AbstractJavaTypeMapper.DEFAULT_CONTENT_CLASSID_FIELD_NAME, Book.class.getName());
            // TODO 如果配置了 params.put("x-message-ttl", 5 * 1000); 那么这一句也可以省略,具体根据业务需要是声明 Queue 的时候就指定好延迟时间还是在发送自己控制时间
            message.getMessageProperties().setExpiration(5 * 1000 + "");
            return message;
        });
        logger.info("[发送时间] - [{}]", LocalDateTime.now());
    }
}
