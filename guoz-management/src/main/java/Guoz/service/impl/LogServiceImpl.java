package Guoz.service.impl;


import Guoz.mapper.LogMapper;
import Guoz.pojo.po.Log;
import Guoz.service.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoz.framework.mybatis.page.DataGrid;
import com.guoz.framework.mybatis.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author Guoz
 */
@Service
public class LogServiceImpl extends BaseServiceImpl<Log> implements LogService {

    private final LogMapper logMapper;

    @Autowired
    public LogServiceImpl(LogMapper logMapper) {
        this.logMapper = logMapper;
    }

    @Override
    public PageInfo<Log> listForDataGrid(DataGrid grid, String datetime) {
        Example example = new Example(Log.class);
        String startTime, endTime;
        if (StringUtils.isNotEmpty(datetime)) {
            String[] time = datetime.split(" - ");
            startTime = time[0];
            endTime = time[1];
            example.createCriteria().andBetween("gmtCreate", startTime, endTime);
        }
        return PageHelper.startPage(grid.getPageNum(), grid.getPageSize()).doSelectPageInfo(() -> this.logMapper.selectByExample(example));
    }
}
