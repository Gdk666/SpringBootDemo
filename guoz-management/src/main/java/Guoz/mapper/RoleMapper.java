package Guoz.mapper;

import com.guoz.framework.mybatis.mapper.BaseMapper;
import Guoz.pojo.dto.RoleOperateDto;
import Guoz.pojo.po.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Guoz
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 根据角色ID查询角色操作项
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<RoleOperateDto> listRoleOperateById(@Param("roleId") Integer roleId);

}