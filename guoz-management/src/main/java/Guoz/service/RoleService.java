package Guoz.service;


import Guoz.pojo.po.Role;
import com.guoz.framework.mybatis.service.BaseService;

import java.util.List;

/**
 * @author Guoz
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 查询所有角色,根据ID排序
     *
     * @return 查询结果
     */
    List<Role> listRole();

    /**
     * 批量删除
     *
     * @param ids ID集
     */
    void deleteRoleAndOperate(Integer[] ids);

}
