package com.wzydev.mapper;

import com.wzydev.pojo.Admin;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    //方式一：手动结果映射
    //    @Results({
    //            @Result(column = "create_time", property = "createTime"),
    //            @Result(column = "update_time", property = "updateTime")
    //    })  // 指定字段名与实体类属性名不一致时，使用@Result注解进行映射

    //方式二：起别名
    //@Select("select id, username, password, create_time createTime, update_time updateTime from admin_info")

    //方式三：开启驼峰命名（在yml配置文件中）
    @Select("select id, username, password, create_time, update_time from admin_info")
    List<Admin> findAll();

    @Delete("delete from admin_info where id = #{id}")
    void deleteById(Integer id);

    /**
     * 这个 @Insert 注解定义了一个 SQL 插入语句，并且将 Admin 对象的属性映射到 SQL 语句中的参数。
     * 这段代码的意思是：
     * 将 Admin 对象中的 username 和 password 字段值插入到 admin_info 表的 username 和 password 列中。
     * 将 Admin 对象中的 createTime 和 updateTime 字段值插入到 admin_info 表的 create_time 和 update_time 列中。
     * 确保 Admin 类中有对应的字段，并且字段名与 MyBatis 的映射配置一致。
     */
    @Insert("insert into admin_info(username, password, create_time, update_time) values (#{username}, #{password}, #{createTime}, #{updateTime})")
    void insert(Admin admin);

    @Select("select id, username, password, create_time, update_time from admin_info where id = #{id}")
    Admin getById(Integer id);

    @Update("update admin_info set username = #{username}, password = #{password}, update_time = #{updateTime} where id = #{id}")
    void update(Admin admin);
}
