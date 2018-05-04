package com.mongodb.mongodemo;

import com.mongodb.mongodemo.Service.serviceimpl.MessageSendServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching //启动缓存 spring 会启动RedisAutoConfiguration
@MapperScan(basePackages ="com.mongodb.mongodemo.Dao")  //自动扫描Dao下的接口
public class MongodemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodemoApplication.class, args);
	}

}
