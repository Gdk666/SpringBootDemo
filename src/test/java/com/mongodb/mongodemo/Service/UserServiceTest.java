package com.mongodb.mongodemo.Service;

import com.mongodb.mongodemo.Service.serviceimpl.MessageSendServiceImpl;
import com.mongodb.mongodemo.utils.SuperNormal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@Transactional
public class UserServiceTest extends SuperNormal{

    @Autowired
    private UserService userService;
    @Autowired
    private MessageSendServiceImpl messageService;

    @Test
    public void send() {
        messageService.sendMsg("test_queue_1", "hello i am delay msg");

    }
}