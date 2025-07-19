package com.wzydev.service.impl;

import com.wzydev.mapper.AdminMapper;
import com.wzydev.pojo.Admin;
import com.wzydev.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> findAll() {
        return adminMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    @Override
    public void add(Admin admin) {
         //1.补全基础属性-createTime、updateTime
        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        //2.调用Mapper接口方法插入数据
        adminMapper.insert(admin);
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin admin) {
        //1.补全基础属性-updateTime
        admin.setUpdateTime(LocalDateTime.now());
        //2.调用Mapper接口方法更新数据
        adminMapper.update(admin);
    }

}
