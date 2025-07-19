package com.wzydev.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer deptId;
    private String name;
    private String photo;
    private String phone;
    private Integer gender;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    //封装部门名称
    private String deptName;  //user_info表中并没有这个字段，封装它只是为了让sql返回指定的字段，见UserMapper中那条被注释掉的select语句
    //封装用户经历类
    private List<UserExpr> userExprList;
}
