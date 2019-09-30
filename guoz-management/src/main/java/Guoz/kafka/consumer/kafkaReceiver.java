package Guoz.kafka.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import Guoz.kafka.beans.Message;
import Guoz.kafka.constant.TopicConst;

/**
 * TODO
 *
 * @author 1
 * @version V1.0
 * @since 2019-06-12 13:41
 */
@Component
public class kafkaReceiver {
    private static Logger logger = LoggerFactory.getLogger(kafkaReceiver.class);

    //这里的topics必须和send一致才可以收到信息
    @KafkaListener(topics = { TopicConst.EXECUTOR_TOPIC},groupId = TopicConst.EXECUTOR_TOPIC)
    public void listen(String message,@Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        logger.info("消费 message:{}",message);
        logger.info("消费 topic:{}",topic);
    }
}
