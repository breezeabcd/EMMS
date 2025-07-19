package com.wzydev.service.impl;

import com.wzydev.mapper.UserExprMapper;
import com.wzydev.mapper.UserMapper;
import com.wzydev.pojo.*;
import com.wzydev.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wzydev.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserExprMapper userExprMapper;

    //原始分页查询
    /*@Override
    public PageResult<User> page(Integer page, Integer pageSize) {
        //1.调用Mapper接口，查询总记录数
        Long total = userMapper.count();
        //2.调用Mapper接口，查询结果列表
        Integer start = (page - 1) * pageSize;
        List<User> rows = userMapper.list(start, pageSize);
        //3.封装结果ResultPage
        return new PageResult<User>(total, rows);
    }*/

    //pageHelper实现分页查询（使用请求参数类）
    //注意事项：
    //      1.定义的SQL语句结尾不能加分号，注释也不要有
    //      2.PagerHelper仅仅能对紧跟在其后的第一个查询语句进行分页处理
    //      比如List<User> userList = userMapper.list()后面还有个
    //         List<User> userList2 = userMapper.list()，
    //         此时只会对前一个 userList进行分页
    @Override
    public PageResult<User> page(UserQueryParam userQueryParam) {
        //1.设置分页参数
        //具体过程：
        //创建一个 Page 对象，并将传入的 page（页码）和 pageSize（每页记录数）参数设置到这个 Page 对象中
        //把 Page 对象存储到当前线程的 ThreadLocal 变量中，这样做是为了在后续 SQL 执行查询操作时
        // PageHelper 的拦截器能够从 ThreadLocal 中获取到分页参数，从而对 SQL 语句进行相应的修改以实现分页功能

        PageHelper.startPage(userQueryParam.getPage(), userQueryParam.getPageSize());

        //2.执行查询
        //具体过程：
        //拦截器会从 ThreadLocal 中获取到之前存储的分页参数
        //根据获取到的分页参数（页码和每页记录数）以及数据库类型自动修改原始的 SQL 语句
        //在 SQL 语句中添加分页相关的 SQL 片段。对于 MySQL 数据库，可能会添加 LIMIT 关键字及对应的偏移量和记录数参数
        //使得查询结果只返回指定页码和页大小范围内的数据

        List<User> userList = userMapper.list(userQueryParam);

        //3.解析查询结果并封装
        //具体过程：
        //由于前面已经调用了 PageHelper.startPage(page, pageSize)，
        //所以 userList 实际上是一个 Page 对象的实例，又由于它继承自 ArrayList ，而ArrayList又是List接口的实现类，所以可以用List接收Page，
        //后面通过强制类型转换，将 userList 转换为 Page<User> 类型的对象 p
        //这样就可以通过 p对象获取到更多分页相关的信息，例如 getTotal()和 getResult()
        //至于为什么不直接写成 Page<User> p = userMapper.list()，因为要考虑规范性和易读性，
        //先写成List<User>更能让程序员清楚

        Page<User> p = (Page<User>) userList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void save(User user) {
        //1.保存用户基本信息
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);

        //2.保存用户工作经历信息
        List<UserExpr> userExprList = user.getUserExprList();
        if(!CollectionUtils.isEmpty(userExprList)) {
            //遍历集合，为userExprList中每个UserExpr对象的userId赋值
            userExprList.forEach(userExpr -> userExpr.setUserId(user.getId()));
            userExprMapper.insertBatch(userExprList);
        }
    }

    @Override
    public void delete(List<Integer> ids) {
        //1.批量删除用户基本信息
        userMapper.deleteByIds(ids);
        //2.批量删除用户工作经历信息
        userExprMapper.deleteByUserIds(ids);
    }

    @Override
    public User getInfo(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        //1.修改用户基本信息
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);

        //2.修改用户工作经历信息
        //2.1.先根据用户id删除该用户原有工作经历信息
        userExprMapper.deleteByUserIds(Arrays.asList(user.getId()));  //因为deleteByUserIds()方法要求参数类型为List<Integer>，所以这里将user.getId()封装成List<Integer>
        //2.2再添加该用户新的工作经历信息
        List<UserExpr> userExprList = user.getUserExprList();
        if(!CollectionUtils.isEmpty(userExprList)) {
            //为userExprList中每个UserExpr对象的userId赋值
            userExprList.forEach(userExpr -> userExpr.setUserId(user.getId()));
            userExprMapper.insertBatch(userExprList);
        }
    }

    @Override
    public LoginInfo login(User user) {
        //1.根据用户名和密码查询用户信息
        User u = userMapper.selectByUsernameAndPassword(user);
        //2.如果查询到用户信息，则组装登陆成功信息并返回
        if(u != null) {
            //生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            String jwt = JwtUtils.generateToken(claims);
            return new LoginInfo(u.getId(), u.getUsername(), u.getName(), jwt);
        }
        //3.不存在则返回null
        return null;
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
