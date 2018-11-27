package Guoz.service.impl;


import Guoz.mapper.RoleMapper;
import Guoz.mapper.RoleOperateMapper;
import Guoz.pojo.po.Role;
import Guoz.service.RoleService;
import com.google.common.collect.Lists;
import com.guoz.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.orderbyhelper.OrderByHelper;

import java.util.List;

/**
 * @author Guoz
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    private final RoleMapper roleMapper;
    private final RoleOperateMapper roleOperateMapper;

    @Autowired
    public RoleServiceImpl(RoleMapper roleMapper, RoleOperateMapper roleOperateMapper) {
        this.roleMapper = roleMapper;
        this.roleOperateMapper = roleOperateMapper;
    }

    @Override
    public List<Role> listRole() {
        OrderByHelper.orderBy("ID ASC");
        return roleMapper.selectAll();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRoleAndOperate(Integer[] ids) {
        Lists.newArrayList(ids).forEach(id -> {
            super.deleteById(id);
            this.roleOperateMapper.deleteRoleOperateByRoleId(id);
        });
    }

}
