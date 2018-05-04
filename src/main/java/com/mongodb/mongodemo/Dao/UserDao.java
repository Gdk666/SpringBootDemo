package com.mongodb.mongodemo.Dao;

import java.util.List;
import com.mongodb.mongodemo.model.User;
import org.springframework.data.domain.Pageable;


/**
 * userDao接口定义
 *
 */
public interface UserDao {

    List<User> findAll();

    User selectByPrimaryKey(Integer id);

    void update(User user);

    void insert(User user);

    void insertAll(List<User> users);

    void remove(Integer id);

    List<User> findByPage(User user, Pageable pageable);

}