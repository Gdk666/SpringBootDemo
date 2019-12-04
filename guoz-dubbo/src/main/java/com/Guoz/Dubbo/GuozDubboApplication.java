package com.Guoz.Dubbo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@EnableDubboConfiguration
@ImportResource(value = {"classpath:application-dubbo.xml"})
public class GuozDubboApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuozDubboApplication.class, args);
	}

}

