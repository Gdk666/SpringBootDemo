package Guoz.kafka.provider;

import com.alibaba.fastjson.JSON;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import Guoz.kafka.constant.TopicConst;

/**
 * TODO
 *
 * @author 1
 * @version V1.0
 * @since 2019-06-12 11:23
 */
@Component
public class kafkaSender<T> {

    private static Logger logger = LoggerFactory.getLogger(kafkaSender.class);

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    //发送消息
    public void send(T obj){
        String jsonObj = JSON.toJSONString(obj);
        logger.info("发送信息内容：{}",jsonObj);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TopicConst.EXECUTOR_TOPIC,jsonObj);
        //获取回调
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.info("onFailure");
            }

            @Override
            public void onSuccess(@Nullable SendResult<String, Object> stringObjectSendResult) {
                logger.info("onSuccess,entity:{}",jsonObj);
            }
        });

    }
}
