package Guoz.config.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @Data 13:51
 * @Version 1.0
 **/
@Configuration
public class QueueConfig {
    /**
     * Exchange()消息交换机，指定消息按什么规则，路由到哪个队列
     * @return
     */
    @Bean
    public CustomExchange Exchange(){
        Map<String ,Object> map = new HashMap<>();
        /**
         * 分为四种：
         * fanout：该类型路由规则非常简单，会把所有发送到该Exchange的消息路由到所有与它绑定的Queue中，相当于广播功能
         * direct：该类型路由规则会将消息路由到binding key与routing key完全匹配的Queue中
         * topic：与direct类型相似，只是规则没有那么严格，可以模糊匹配和多条件匹配
         * headers：该类型不依赖于routing key与binding key的匹配规则来路由消息，而是根据发送的消息内容中的headers属性进行匹配
         */
        map.put("x-delayed-type","direct");
        map.put("x-queue-mode","lazy");
        return new CustomExchange("test_exchange","x-delayed-message",true,false,map);
    }

    /**
     * queue 消息队列，每个消息会投入到一个或者多个队列
     * @return
     */
    @Bean
    public Queue queue(){
        Queue queue = new Queue("test_queue_1",true);

        return queue;
    }
    @Bean
    public Queue queue1(){
        Queue queue = new Queue("test_queue_2",true);
        return queue;
    }

    /**
     * 绑定
     * @return
     */
    @Bean
    public Binding binding(){

        return BindingBuilder.bind(queue()).to(Exchange()).with("test_queue_1").noargs();
    }
    @Bean
    public Binding binding1(){

        return BindingBuilder.bind(queue1()).to(Exchange()).with("test_queue_2").noargs();
    }

}
