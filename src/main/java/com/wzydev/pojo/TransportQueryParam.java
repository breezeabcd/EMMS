package com.wzydev.pojo;

import lombok.Data;

@Data
public class TransportQueryParam {
     private Integer page = 1;
     private Integer pageSize = 10;
     private String destination;
     private String supplyName;
}
