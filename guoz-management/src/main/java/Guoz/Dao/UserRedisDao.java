package Guoz.Dao;


import Guoz.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * userDao接口定义
 *
 */
public interface UserRedisDao {

    List<User> findAll();

    void update(User user);

    void insert(User user);

    void insertAll(List<User> users);

    void remove(Integer id);

    List<User> findByPage(User user, Pageable pageable);

}