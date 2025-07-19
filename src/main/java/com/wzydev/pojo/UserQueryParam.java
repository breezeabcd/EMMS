package com.wzydev.pojo;

import lombok.Data;

//查询参数过多或后续可能频繁增加时，可以创建一个查询参数类，用于封装查询参数
@Data
public class UserQueryParam {
    private Integer page = 1;
    private Integer pageSize = 10;
    private String name;
    private String phone;
    private Integer gender;
}
