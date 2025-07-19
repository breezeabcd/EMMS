package com.wzydev.mapper;

import com.wzydev.pojo.User;
import com.wzydev.pojo.UserQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {
    //===================================原始分页查询实现==================================
    /**
     * 查询总记录数
     */
//    @Select("select count(*) from user_info u left join dept_info d on u.dept_id = d.id")
//    public Long count();

    /**
     * 分页查询
     */
//    @Select("select u.*, d.name deptName from user_info u left join dept_info d on u.dept_id = d.id " +
//            "order by update_time desc limit #{start},#{pageSize}")  //d.id"和+之间必须用空格分隔开
//    public List<User> list(Integer start, Integer pageSize);


    //===================================pageHelper实现分页查询==================================
    //deptName也可以换成dept_name，dn之类的，随便
//    @Select("select u.*, d.name deptName from user_info u left join dept_info d on u.dept_id = d.id " +
//            "order by update_time desc ")  //删去limit相关查询条件
    List<User> list(UserQueryParam userQueryParam);  //已在xml文件中配置sql语句

    /**
     * 添加用户
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")  //获取到插入后生成的主键，并将值赋给user对象的id属性
    @Insert("insert into user_info(username, dept_id, name, photo, phone, gender, create_time, update_time)" +
            " values (#{username}, #{deptId}, #{name}, #{photo}, #{phone}, #{gender}, #{createTime}, #{updateTime})")
    void insert(User user);

    /**
     * 批量删除用户
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据id查询用户信息以及用户工作经历信息
     */
    User getById(Integer id);

    /**
     * 根据id修改用户信息
     */
    void updateById(User user);

    /**
     * 根据用户名和密码查询用户信息
     */
    @Select("select id, username, name from user_info where username = #{username} and password = #{password}")
    User selectByUsernameAndPassword(User user);

    @Select("select * from user_info")
    List<User> findAll();
}
