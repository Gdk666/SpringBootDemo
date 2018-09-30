package Guoz.service;


import Guoz.pojo.po.Log;
import com.github.pagehelper.PageInfo;
import com.guoz.framework.mybatis.page.DataGrid;
import com.guoz.framework.mybatis.service.BaseService;

/**
 * @author Guoz
 */
public interface LogService extends BaseService<Log> {

    PageInfo<Log> listForDataGrid(DataGrid grid, String datetime);

}
