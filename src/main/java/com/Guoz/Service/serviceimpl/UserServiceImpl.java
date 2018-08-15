package com.Guoz.Service.serviceimpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.Guoz.Dao.UserDao;
import com.Guoz.Service.UserService;
import com.Guoz.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
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
    @Cacheable(value = "user",key = "'user_'+#id")
    public User getUser(Integer id) {
        System.out.println("进入getUser方法");
        String sql = "select * from user where id = ?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public void insertAll(List<User> users) {

    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public List<User> findByPage(User user, Pageable pageable) {
        return userDao.findByPage(user, pageable);
    }

    @Override
    @Cacheable
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
        User user = this.getUser(id);
        operations.set(key,user,10, TimeUnit.SECONDS);
        return user;
    }


}