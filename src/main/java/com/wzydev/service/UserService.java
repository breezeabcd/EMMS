package com.wzydev.service;

import com.wzydev.pojo.LoginInfo;
import com.wzydev.pojo.PageResult;
import com.wzydev.pojo.User;
import com.wzydev.pojo.UserQueryParam;

import java.util.List;

public interface UserService {

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     * @return
     */
//    PageResult<User> page(Integer page, Integer pageSize, String name, Integer gender);

    /**
     * 分页查询（使用请求参数类）
     */
    PageResult<User> page(UserQueryParam userQueryParam);

    /**
     * 保存用户及用户工作经历信息
     * @param user
     */
    void save(User user);

    /**
     * 删除用户及用户工作经历信息
     * @param ids
     */
    void delete(List<Integer> ids);

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    User getInfo(Integer id);

    /**
     * 更新用户及用户工作经历信息
     * @param user
     */
    void update(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    LoginInfo login(User user);

    List<User> findAll();
}
