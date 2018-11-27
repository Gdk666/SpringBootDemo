package Guoz.service;


import com.guoz.framework.commons.model.TreeNode;
import com.guoz.framework.mybatis.page.DataGrid;
import com.guoz.framework.mybatis.service.BaseService;
import Guoz.pojo.dto.PermissionDto;
import Guoz.pojo.po.Menu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Guoz
 * @version 2.5.1
 * @since 2018-01-10
 */
public interface MenuService extends BaseService<Menu> {

    /**
     * 分页查询 菜单列表
     *
     * @param grid 分页信息
     * @return 查询结果
     */
    PageInfo<Menu> listMenuForDataGrid(DataGrid grid);

    /**
     * 根据角色ID获取树形结构的菜单数据
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<TreeNode> listTree(Integer roleId);

    /**
     * 查询所有权限信息
     *
     * @return 查询结果
     */
    List<PermissionDto> listPermissions();

    /**
     * 查询所有菜单
     *
     * @return 查询结果
     */
    List<Menu> listMenu();

    /**
     * 根据菜单编号判断 添加/保存 菜单信息
     *
     * @param menu 菜单
     */
    void saveOrUpdate(Menu menu);

}
