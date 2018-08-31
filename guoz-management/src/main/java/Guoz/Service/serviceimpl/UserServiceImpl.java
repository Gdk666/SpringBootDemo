package Guoz.Service.serviceimpl;


import Guoz.Dao.UserDao;
import Guoz.Service.UserService;
import Guoz.model.User;
import com.github.pagehelper.PageInfo;
import com.guoz.framework.commons.Exception.CustomException;
import com.guoz.framework.mybatis.page.DataGrid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@SuppressWarnings("all")
@Service("UserService")
public class UserServiceImpl implements UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserDao userDao;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql,new Object[]{},new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    @Cacheable(value = "User",keyGenerator = "smpkeyGenerator")
    public User getUser111(Long id) {
        System.out.println("进入getUser方法");
        String sql = "select * from user where id = ?";
        try {
            User o = jdbcTemplate.queryForObject(sql,new Object[]{id},new BeanPropertyRowMapper<>(User.class));
            if (o == null){
                throw new CustomException("111",400);
            }
            return o;
        }catch (EmptyResultDataAccessException e){
            return null;
        }




    }



    @Override
    public boolean insert(User entity) {
        return false;
    }

    @Override
    public boolean insertSelective(User entity) {
        return false;
    }

    @Override
    public List<User> findByPage(User user, Pageable pageable) {
        return userDao.findByPage(user, pageable);
    }


    @Override
    public boolean deleteById(Object key) {
        return false;
    }

    @Override
    public Optional selectById(Object key) {
        return null;
    }

    @Override
    public boolean updateById(User entity) {
        return false;
    }

    @Override
    public boolean updateSelectiveById(User entity) {
        return false;
    }


    @Override
    public List listAll() {
        return null;
    }

    @Override
    public PageInfo listForDataGrid(DataGrid grid) {
        return null;
    }

    @Override
    public PageInfo<User> listForDataGrid(DataGrid grid, User entity) {
        return null;
    }

}