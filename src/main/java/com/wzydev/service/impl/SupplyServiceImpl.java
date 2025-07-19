package com.wzydev.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wzydev.mapper.SupplyMapper;
import com.wzydev.pojo.*;
import com.wzydev.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SupplyServiceImpl implements SupplyService {
    @Autowired
    private SupplyMapper supplyMapper;

    @Override
    public PageResult<Supply> page(SupplyQueryParam supplyQueryParam) {
        PageHelper.startPage(supplyQueryParam.getPage(), supplyQueryParam.getPageSize());

        List<Supply> supplyList = supplyMapper.list(supplyQueryParam);

        Page<Supply> p = (Page<Supply>) supplyList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        supplyMapper.deleteByIds(ids);
    }

    @Override
    public void add(Supply supply) {
        supply.setCreateTime(LocalDateTime.now());
        supply.setUpdateTime(LocalDateTime.now());
        supplyMapper.insert(supply);
    }

    @Override
    public Supply getById(Integer id) {
        return supplyMapper.getById(id);
    }

    @Override
    public void update(Supply supply) {
        supply.setUpdateTime(LocalDateTime.now());
        supplyMapper.update(supply);
    }

    @Override
    public List<Supply> findAll() {
        return supplyMapper.findAll();
    }
}
