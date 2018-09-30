package Guoz.service.impl;


import Guoz.mapper.OperateMapper;
import Guoz.pojo.dto.OperateDto;
import Guoz.pojo.dto.ShiroPermission;
import Guoz.pojo.po.Operate;
import Guoz.service.OperateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoz.framework.mybatis.page.DataGrid;
import com.guoz.framework.mybatis.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Guoz
 */
@Service
public class OperateServiceImpl extends BaseServiceImpl<Operate> implements OperateService {

    private final OperateMapper operateMapper;

    @Autowired
    public OperateServiceImpl(OperateMapper operateMapper) {
        this.operateMapper = operateMapper;
    }

    @Override
    public List<Operate> listOperateByRoleIdAndMenuId(Integer roleId, Integer menuId) {
        return this.operateMapper.listOperateByRoleIdAndMenuId(roleId, menuId);

    }

    @Override
    public PageInfo<OperateDto> listOperateByPage(DataGrid grid) {
        grid.getOrderBy();
        return PageHelper.startPage(grid.getPageNum(), grid.getPageSize())
                .doSelectPageInfo(this.operateMapper::listOperateByPage);
    }

    @Override
    public List<ShiroPermission> listShiroPermissions(Integer roleId) {
        return this.operateMapper.listShiroPermissionByRoleId(roleId);
    }

}
