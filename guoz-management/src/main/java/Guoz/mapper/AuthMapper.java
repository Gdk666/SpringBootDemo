package Guoz.mapper;

import Guoz.pojo.po.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Guoz
 */
@Mapper
public interface AuthMapper {

    /**
     * 根据角色ID查询可操作的菜单
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<Menu> listMenuByRoleId(@Param("roleId") Integer roleId);
}
