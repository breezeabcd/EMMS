package com.wzydev.mapper;

import com.wzydev.pojo.Transport;
import com.wzydev.pojo.TransportQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransportMapper {
    List<Transport> list(TransportQueryParam transportQueryParam);

    void deleteByIds(List<Integer> ids);

    @Insert("insert into transport(supply_id, destination, quantity, detail, create_time, update_time) values (#{supplyId}, #{destination}, #{quantity}, #{detail}, #{createTime}, #{updateTime})")
    void insert(Transport transport);

    void update(Transport transport);

    Transport getById(Integer id);
}
