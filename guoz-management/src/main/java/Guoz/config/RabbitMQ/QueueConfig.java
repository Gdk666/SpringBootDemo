package Guoz.config.RabbitMQ;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
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
     * 延迟队列 TTL 名称
     */
    private static final String REGISTER_DELAY_QUEUE = "dev.delay.queue";
    /**
     * DLX，dead letter发送到的 exchange
     * TODO 此处的 exchange 很重要,具体消息就是发送到该交换机的
     */
    public static final String REGISTER_DELAY_EXCHANGE = "dev.delay.exchange";
    /**
     * routing key 名称
     * TODO 此处的 routingKey 很重要,具体消息发送在该 routingKey 的
     */
    public static final String DELAY_ROUTING_KEY = "";


    public static final String REGISTER_QUEUE_NAME = "dev.register.queue";
    public static final String REGISTER_EXCHANGE_NAME = "dev.register.exchange";
    public static final String ROUTING_KEY = "all";

    /**
     * 延迟队列配置
     * <p>
     * 1、params.put("x-message-ttl", 5 * 1000);
     * TODO 第一种方式是直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活,（当然二者是兼容的,默认是时间小的优先）
     * 2、rabbitTemplate.convertAndSend(book, message -> {
     * message.getMessageProperties().setExpiration(2 * 1000 + "");
     * return message;
     * });
     * TODO 第二种就是每次发送消息动态设置延迟时间,这样我们可以灵活控制
     **/
    @Bean
    public Queue delayProcessQueue() {
        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", REGISTER_EXCHANGE_NAME);
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", ROUTING_KEY);
        return new Queue(REGISTER_DELAY_QUEUE, true, false, false, params);
    }

    /**
     * 需要将一个队列绑定到交换机上，要求该消息与一个特定的路由键完全匹配。
     * 这是一个完整的匹配。如果一个队列绑定到该交换机上要求路由键 “dog”，则只有被标记为“dog”的消息才被转发，不会转发dog.puppy，也不会转发dog.guard，只会转发dog。
     * TODO 它不像 TopicExchange 那样可以使用通配符适配多个
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(REGISTER_DELAY_EXCHANGE);
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(delayProcessQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY);
    }


    @Bean
    public Queue registerBookQueue() {
        return new Queue(REGISTER_QUEUE_NAME, true);
    }

    /**
     * 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。
     * 符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。因此“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”。
     **/
    @Bean
    public TopicExchange registerBookTopicExchange() {
        return new TopicExchange(REGISTER_EXCHANGE_NAME);
    }

    @Bean
    public Binding registerBookBinding() {
        // TODO 如果要让延迟队列之间有关联,这里的 routingKey 和 绑定的交换机很关键
        return BindingBuilder.bind(registerBookQueue()).to(registerBookTopicExchange()).with(ROUTING_KEY);
    }





    /**
     * Exchange()消息交换机，指定消息按什么规则，路由到哪个队列
     * @return
     */
    @Bean
    public CustomExchange Exchange(){
        Map<String ,Object> map = new HashMap<>();
//        *
//         * 分为四种：
//         * fanout：该类型路由规则非常简单，会把所有发送到该Exchange的消息路由到所有与它绑定的Queue中，相当于广播功能
//         * direct：该类型路由规则会将消息路由到binding key与routing key完全匹配的Queue中
//         * topic：与direct类型相似，只是规则没有那么严格，可以模糊匹配和多条件匹配
//         * headers：该类型不依赖于routing key与binding key的匹配规则来路由消息，而是根据发送的消息内容中的headers属性进行匹配

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
