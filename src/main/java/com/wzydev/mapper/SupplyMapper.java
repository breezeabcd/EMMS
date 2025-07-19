package com.wzydev.mapper;

import com.wzydev.pojo.Dept;
import com.wzydev.pojo.Supply;
import com.wzydev.pojo.SupplyQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SupplyMapper {

    List<Supply> list(SupplyQueryParam supplyQueryParam);

    void deleteByIds(List<Integer> ids);

    @Insert("insert into supply_info(name, quantity, photo, create_time, update_time) values (#{name}, #{quantity}, #{photo}, #{createTime}, #{updateTime})")
    void insert(Supply supply);

    @Select("select id, name, quantity, photo, create_time, update_time from supply_info where id = #{id}")
    Supply getById(Integer id);

    void update(Supply supply);

    @Update("update supply_info set quantity = quantity - #{quantity} where id = #{id}")
    void decreaseQuantity(Integer id, Integer quantity);

    @Select("select * from supply_info")
    List<Supply> findAll();
}
