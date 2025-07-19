package com.wzydev.service;

import com.wzydev.pojo.PageResult;
import com.wzydev.pojo.RequisitionQueryParam;
import com.wzydev.pojo.Requisition;

import java.util.List;

public interface RequisitionService {
    PageResult<Requisition> page(RequisitionQueryParam requisitionQueryParam);

    void delete(List<Integer> ids);

    void add(Requisition requisition);

    Requisition getInfo(Integer id);

    void update(Requisition requisition);

    String approve(Integer id);
}
