package Guoz.Service;


import Guoz.model.User;
import com.guoz.framework.mybatis.service.BaseService;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService extends BaseService<User>{

    List<User> findAll();

    User getUser111(Long id);

    List<User> findByPage(User user, Pageable pageable);

}