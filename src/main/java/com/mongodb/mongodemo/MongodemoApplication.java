package com.mongodb.mongodemo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;


@EnableCaching //启动缓存 spring 会启动RedisAutoConfiguration
@MapperScan(basePackages ="com.mongodb.mongodemo.Dao")  //自动扫描Dao下的接口
@SpringBootApplication
public class MongodemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodemoApplication.class, args);
	}


}
