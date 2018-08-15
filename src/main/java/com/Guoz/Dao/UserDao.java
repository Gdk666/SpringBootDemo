package com.Guoz.Dao;

import java.util.List;

import com.Guoz.model.User;
import com.Guoz.utils.common.config.BaseDao;
import org.springframework.data.domain.Pageable;


/**
 * userDao接口定义
 *
 */

public interface UserDao extends BaseDao<User> {

    List<User> findAll();

    List<User> findByPage(User user, Pageable pageable);

    /**
     * 根据用户名统计（TODO 假设它是一个很复杂的SQL）
     *
     * @param userName 用户名
     * @return 统计结果
     */
    int countByUsername(String userName);

}