package Guoz.service;


import Guoz.pojo.message.ApiResult;
import Guoz.pojo.po.RoleOperate;
import com.guoz.framework.mybatis.service.BaseService;

import java.util.List;

/**
 * @author Guoz
 */
public interface RoleOperateService extends BaseService<RoleOperate> {

    /**
     * 根据角色ID查询 角色操作信息
     *
     * @param roleId 角色ID
     * @return 查询结果
     */
    List<RoleOperate> listRoleOperateByRoleId(Integer roleId);

    /**
     * 批量添加 角色操作信息
     *
     * @param operateId 操作ID
     * @param roleId    角色ID
     * @return 操作结果
     */
    ApiResult<String> batchInsertRoleOperate(Integer[] operateId, Integer roleId);

}
