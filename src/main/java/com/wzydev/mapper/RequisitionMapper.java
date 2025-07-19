package com.wzydev.mapper;

import com.wzydev.pojo.Requisition;
import com.wzydev.pojo.RequisitionQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RequisitionMapper {
    List<Requisition> list(RequisitionQueryParam requisitionQueryParam);

    void deleteByIds(List<Integer> ids);

    @Insert("insert into requisition(user_id, supply_id, quantity, opinion, create_time, update_time) values (#{userId}, #{supplyId}, #{quantity}, #{opinion}, #{createTime}, #{updateTime})")
    void insert(Requisition requisition);

    Requisition getById(Integer id);

    void updateById(Requisition requisition);
}
