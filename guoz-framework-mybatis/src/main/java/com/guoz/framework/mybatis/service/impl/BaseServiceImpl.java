package com.guoz.framework.mybatis.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.guoz.framework.mybatis.mapper.BaseMapper;
import com.guoz.framework.mybatis.page.DataGrid;
import com.guoz.framework.mybatis.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * 基础操作
 *
 * @param <T> 泛型
 * @author Guoz
 * @version 2.5.1
 * @since 2018-01-10
 */
@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseMapper<T> mapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insert(T entity) {
        return mapper.insertSelective(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean insertSelective(T entity) {
        return mapper.insertSelective(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteById(Object key) {
        return mapper.deleteByPrimaryKey(key) > 0;
    }

    @Override
    public Optional<T> selectById(Object key) {
        return Optional.ofNullable(mapper.selectByPrimaryKey(key));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(T entity) {
        return mapper.updateByPrimaryKey(entity) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateSelectiveById(T entity) {
        return mapper.updateByPrimaryKeySelective(entity) > 0;
    }

    @Override
    public List<T> listAll() {
        return this.mapper.selectAll();
    }


    public PageInfo<T> listForDataGrid(DataGrid grid) {
        return this.listForDataGrid(grid, null);
    }


    public PageInfo<T> listForDataGrid(DataGrid grid, T entity) {
        grid.getOrderBy();
        PageHelper.startPage(grid.getPageNum(), grid.getPageSize());
        return new PageInfo<>(mapper.select(entity));
    }


}
