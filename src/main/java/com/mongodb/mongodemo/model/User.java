package com.mongodb.mongodemo.model;

import java.io.Serializable;
import org.springframework.data.annotation.Id;

/**
 * user 实体类
 *
 */
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    private Integer id;

    private String userName;

    private String password;

    private Integer age;

    public User(Integer id, String userName, String password, Integer age) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.age = age;
    }

    public User(Integer id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", age=" + age + "]";
    }

}