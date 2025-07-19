package com.wzydev.service;

import com.wzydev.pojo.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> findAll();

    void deleteById(Integer id);

    void add(Admin admin);

    Admin getById(Integer id);

    void update(Admin admin);
}
