package Guoz.service;

import com.guoz.framework.mybatis.page.DataGrid;
import com.guoz.framework.mybatis.service.BaseService;
import Guoz.pojo.dto.ManagerDto;
import Guoz.pojo.po.Manager;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Guoz
 * @version 2.5.1
 * @since 2018-01-10
 */
public interface ManagerService extends BaseService<Manager> {

    /**
     * 带分页查询账户信息
     *
     * @param grid    分页信息
     * @param name    名称
     * @param account 账户
     * @return 查询结果
     */
    PageInfo<ManagerDto> listManagerByName(DataGrid grid, String name, String account);

    /**
     * 根据账号查询信息
     *
     * @param account 账号
     * @return 查询结果
     */
    ManagerDto selectManagerByAccount(String account);

    ManagerDto getById(String id);

    void updateBch(List<Manager> list);

}
