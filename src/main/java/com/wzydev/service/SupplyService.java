package com.wzydev.service;

import com.wzydev.pojo.PageResult;
import com.wzydev.pojo.Supply;
import com.wzydev.pojo.SupplyQueryParam;

import java.util.List;

public interface SupplyService {

    void delete(List<Integer> ids);

    void add(Supply supply);

    Supply getById(Integer id);

    void update(Supply supply);

    PageResult<Supply> page(SupplyQueryParam supplyQueryParam);

    List<Supply> findAll();
}
