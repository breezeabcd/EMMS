package com.wzydev.service;

import com.wzydev.pojo.PageResult;
import com.wzydev.pojo.Transport;
import com.wzydev.pojo.TransportQueryParam;

import java.util.List;

public interface TransportService {
    PageResult<Transport> page(TransportQueryParam transportQueryParam);

    void delete(List<Integer> ids);

    void add(Transport transport);

    void update(Transport transport);

    Transport getById(Integer id);
}
