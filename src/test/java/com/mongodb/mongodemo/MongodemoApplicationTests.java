package com.mongodb.mongodemo;

import com.mongodb.mongodemo.Service.serviceimpl.MessageSendServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MongodemoApplicationTests {
	@Autowired
	private MessageSendServiceImpl messageService;

	@Test
	public void send() {
		messageService.sendMsg("test_queue_1","hello i am delay msg");
	}

}
