package com.mongodb.mongodemo.Dao.DaoImpl;

import java.util.List;

import com.mongodb.mongodemo.Dao.UserDao;
import com.mongodb.mongodemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


/**
 * 接口实现
 * 
 *
 */
@Repository("UserDao")
public class UserDaoImpl implements UserDao {

    /**
     * 由springboot自动注入，默认配置会产生mongoTemplate这个bean
     */
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查找全部
     */
    @Override
    public List<User> findAll() {
        return mongoTemplate.findAll(User.class);
    }

    /**
     * 根据id得到对象
     */
    @Override
    public User selectByPrimaryKey(Integer id) {
        mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), User.class);

        return null;
    }

    /**
     * 插入一个用户
     */
    @Override
    public void insert(User user) {
        mongoTemplate.insert(user);
    }

    /**
     * 根据id删除一个用户
     */
    @Override
    public void remove(Integer id) {
        Criteria criteria = Criteria.where("id").is(id);  
        Query query = new Query(criteria);
        mongoTemplate.remove(query,User.class);
    }

    /**
     * 分页查找
     * 
     * user代表过滤条件
     * 
     * pageable代表分页bean
     */
    @Override
    public List<User> findByPage(User user, Pageable pageable) {
        Query query = new Query();
        if (user != null && user.getUserName() != null) {
            //模糊查询
            query = new Query(Criteria.where("userName").regex("^" + user.getUserName()));
        }
        List<User> list = mongoTemplate.find(query.with(pageable), User.class);
        return list;
    }

    /**
     * 根据id更新
     */
    @Override
    public void update(User user) {
        Criteria criteria = Criteria.where("id").is(user.getId());
        Query query = new Query(criteria);
        Update update = Update.update("userName", user.getUserName()).set("age", user.getAge());
        mongoTemplate.updateMulti(query, update, User.class);
    }

    /**
     * 插入一个集合
     */
    @Override
    public void insertAll(List<User> users) {
        mongoTemplate.insertAll(users);
    }

}