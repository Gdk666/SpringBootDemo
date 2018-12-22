package Guoz.service.impl;


import Guoz.mapper.ManagerMapper;
import Guoz.pojo.dto.ManagerDto;
import Guoz.pojo.po.Manager;
import Guoz.service.ManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoz.framework.mybatis.page.DataGrid;
import com.guoz.framework.mybatis.service.impl.BaseServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author Guoz
 */
@Service
public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements ManagerService {

	private final ManagerMapper managerMapper;

	@Autowired
	public ManagerServiceImpl(ManagerMapper managerMapper) {
		this.managerMapper = managerMapper;
	}

	@Override
	public PageInfo<ManagerDto> listManagerByName(DataGrid grid, String name, String account) {
		grid.getOrderBy();
		return PageHelper.startPage(grid.getPageNum(), grid.getPageSize())
				.doSelectPageInfo(() -> this.managerMapper.listManagerByName(name,account));
	}

	@Override
	public ManagerDto selectManagerByAccount(String account) {
		return this.managerMapper.selectManagerByAccount(account);
	}

	@Override
	public ManagerDto getById(String id) {
		return this.managerMapper.getById(id);
	}

	@Override
	public void updateBch(List<Manager> list) {
		managerMapper.updateBch(list);
	}

}
