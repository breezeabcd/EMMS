package com.wzydev.pojo;

import lombok.Data;

@Data
public class RequisitionQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String applicantName;
    private String supplyName;
    private Integer status;
}
