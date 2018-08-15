package com.Guoz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import tk.mybatis.spring.annotation.MapperScan;


@EnableCaching //启动缓存 spring 会启动RedisAutoConfiguration
@MapperScan(basePackages ="com.Guoz.Dao")  //自动扫描Dao下的接口
@SpringBootApplication
@EnableScheduling
public class MongodemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongodemoApplication.class, args);
	}
	@Bean
	public TaskScheduler taskScheduler(){
		ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setPoolSize(10);
		return threadPoolTaskScheduler;
	}


}


