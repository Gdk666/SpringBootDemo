package com.Guoz.Service;

import com.Guoz.Service.serviceimpl.MessageSendServiceImpl;
import com.Guoz.utils.SuperNormal;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.Guoz.Dao.UserDao;
import com.Guoz.Service.serviceimpl.UserServiceImpl;
import com.Guoz.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@Transactional
public class UserServiceTest extends SuperNormal {
    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private MessageSendServiceImpl messageService;
    @Autowired
    private UserServiceImpl userimpl;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String,Serializable> redisTemplate;
    @Autowired
    private UserDao userDao;

    @Test
    public void send() {
        for(int i= 0 ;i<100;i++){
            messageService.sendMsg("test_queue_1", "hello i am delay msg"+i);
            messageService.sendMsg("test_queue_2", "hello i am delay msg"+i);
        }

    }

    @Test
    public void get(){
        //TODO 测试线程安全
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0,1000).forEach(i -> executorService.execute(() ->
                stringRedisTemplate.opsForValue().increment("kk",1)));
        stringRedisTemplate.opsForValue().set("k1","v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("[字符缓存结果] - [{}]", k1);
        // TODO 以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
        String key = "battcn:user:1";
        redisTemplate.opsForValue().set(key, new User(1111, "u1", 11));
        // TODO 对应 String（字符串）
        final User user = (User) redisTemplate.opsForValue().get(key);
        log.info("[对象缓存结果] - [{}]", user);
    }

    @Test
    public void get1(){
        //TODO 测试线程安全
        User user = userimpl.getUser(2);
        log.info("[对象缓存结果] - [{}]", user);
    }

    @Test
    public void tet(){
        final User user1 = new User(11, "p11",11);
        final User user2 = new User(12, "p12",12);
        final User user3 = new User(13, "p13",13);
        userDao.insertSelective(user1);
        log.info("[user1回写主键] - [{}]", user1.getId());
        userDao.insertSelective(user2);
        log.info("[user2回写主键] - [{}]", user2.getId());
        userDao.insertSelective(user3);
        log.info("[user3回写主键] - [{}]", user3.getId());
        final int count = userDao.countByUsername("p11");
        log.info("[调用自己写的SQL] - [{}]", count);

        // TODO 模拟分页
        userDao.insertSelective(new User(21,"u1", 21));
        userDao.insertSelective(new User(22,"u1", 22));
        userDao.insertSelective(new User(23,"u1", 23));
        userDao.insertSelective(new User(24,"u1", 24));
        userDao.insertSelective(new User(25,"u1", 25));
        userDao.insertSelective(new User(26,"u1", 26));
        userDao.insertSelective(new User(27,"u1", 27));
        userDao.insertSelective(new User(28,"u1", 28));
        userDao.insertSelective(new User(29,"u1", 29));
        userDao.insertSelective(new User(30,"u1", 30));
        userDao.insertSelective(new User(31,"u1", 31));

        // TODO 分页 + 排序 this.userMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        final PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> this.userDao.selectAll());
        log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());

        PageHelper.startPage(1, 10).setOrderBy("id desc");
        final PageInfo<User> userPageInfo = new PageInfo<>(this.userDao.selectAll());
        log.info("[普通写法] - [{}]", userPageInfo);
    }
}