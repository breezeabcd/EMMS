package com.wzydev.mapper;

import com.wzydev.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select id, name, headcount, create_time, update_time from dept_info order by update_time desc")
    List<Dept> findAll();

    @Delete("delete from dept_info where id = #{id}")
    void deleteById(Integer id);

    @Insert("insert into dept_info(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Select("select id, name, headcount, create_time, update_time from dept_info where id = #{id}")
    Dept getById(Integer id);

    @Update("update dept_info set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
