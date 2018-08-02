package com.mongodb.mongodemo.Component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @ClassName Guoz
 * @Data 15:38
 * @Version 1.0
 **/
@Component
@ConfigurationProperties(prefix = "my1")
@PropertySource("classpath:/properties/application.properties")
public class MyProperties1 {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyProperties1{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
