package com.wzydev.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wzydev.mapper.TransportMapper;
import com.wzydev.pojo.PageResult;
import com.wzydev.pojo.Transport;
import com.wzydev.pojo.TransportQueryParam;
import com.wzydev.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {
    @Autowired
    private TransportMapper transportMapper;

    @Override
    public PageResult<Transport> page(TransportQueryParam transportQueryParam) {
        PageHelper.startPage(transportQueryParam.getPage(), transportQueryParam.getPageSize());

        List<Transport> transportList = transportMapper.list(transportQueryParam);

        Page<Transport> p = (Page<Transport>) transportList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(List<Integer> ids) {
        transportMapper.deleteByIds(ids);
    }

    @Override
    public void add(Transport transport) {
        transport.setCreateTime(LocalDateTime.now());
        transport.setUpdateTime(LocalDateTime.now());
        transportMapper.insert(transport);
    }

    @Override
    public void update(Transport transport) {
        transport.setUpdateTime(LocalDateTime.now());
        transportMapper.update(transport);
    }

    @Override
    public Transport getById(Integer id) {
        return transportMapper.getById(id);
    }
}
