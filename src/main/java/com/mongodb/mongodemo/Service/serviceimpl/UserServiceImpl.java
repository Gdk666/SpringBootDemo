package com.mongodb.mongodemo.Service.serviceimpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.mongodb.mongodemo.Dao.UserDao;
import com.mongodb.mongodemo.Service.UserService;
import com.mongodb.mongodemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@SuppressWarnings("all")
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql,new Object[]{},new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getUser(Integer id) {
        String sql = "select * from user where id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void insertAll(List<User> users) {
        userDao.insertAll(users);
    }

    @Override
    public void remove(Integer id) {
        userDao.remove(id);
    }

    @Override
    public List<User> findByPage(User user, Pageable pageable) {
        return userDao.findByPage(user, pageable);
    }

    @Override
    public User findUserById(int id) {
        //从缓存中获取用户信息
        String key = "user_"+id;
        ValueOperations<String,User> operations = redisTemplate.opsForValue();
        //缓存是否存在
        boolean haskey = redisTemplate.hasKey(key);
        if(haskey){
            User user = operations.get(key);
            return  user;
        }
        //从数据库中获取信息
        User user = userDao.selectByPrimaryKey(id);
        operations.set(key,user,10, TimeUnit.SECONDS);
        System.out.println(user.getUserName()+key);
        return user;
    }


}