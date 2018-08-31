package Guoz.Dao;

import Guoz.model.User;


import com.guoz.framework.mybatis.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * userDao接口定义
 *
 */

public interface UserDao extends BaseMapper<User> {

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