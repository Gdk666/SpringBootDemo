package com.Guoz.Service;

import java.util.List;

import com.Guoz.model.User;
import org.springframework.data.domain.Pageable;


public interface UserService {

    List<User> findAll();

    User getUser(Integer id);

    void update(User user);

    void insert(User user);

    void insertAll(List<User> users);

    void remove(Integer id);

    List<User> findByPage(User user,Pageable pageable);

    public User findUserById(int id);
}