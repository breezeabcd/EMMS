package com.wzydev.pojo;

import lombok.Data;

@Data
public class SupplyQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
}
