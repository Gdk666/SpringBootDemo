package com.mongodb.mongodemo.Service;

import com.mongodb.mongodemo.Service.serviceimpl.MessageSendServiceImpl;
import com.mongodb.mongodemo.Service.serviceimpl.UserServiceImpl;
import com.mongodb.mongodemo.model.User;
import com.mongodb.mongodemo.utils.SuperNormal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@Transactional
public class UserServiceTest extends SuperNormal{
    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private MessageSendServiceImpl messageService;
    @Autowired
    private UserServiceImpl userimpl;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;

    @Test
    public void send() {
        for(int i= 0 ;i<100;i++){
            messageService.sendMsg("test_queue_1", "hello i am delay msg"+i);
            messageService.sendMsg("test_queue_2", "hello i am delay msg"+i);
        }

    }

    @Test
    public void get(){
        //TODO 测试线程安全
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0,1000).forEach(i -> executorService.execute(() ->
                stringRedisTemplate.opsForValue().increment("kk",1)));
        stringRedisTemplate.opsForValue().set("k1","v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("[字符缓存结果] - [{}]", k1);
        // TODO 以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
        String key = "battcn:user:1";
        redisTemplate.opsForValue().set(key, new User(1111, "u1", 11));
        // TODO 对应 String（字符串）
        final User user = (User) redisTemplate.opsForValue().get(key);
        log.info("[对象缓存结果] - [{}]", user);
    }

    @Test
    public void get1(){
        //TODO 测试线程安全
        User user = userimpl.getUser(2);
        log.info("[对象缓存结果] - [{}]", user);
    }
}