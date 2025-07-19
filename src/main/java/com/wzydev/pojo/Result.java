package com.wzydev.pojo;

import lombok.Data;

@Data
public class Result {
    private Integer code;  //编码，1表示成功，0表示失败
    private String msg;  //错误信息
    private Object data;  //返回的数据


    // 成功静态方法，且无返回数据
    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    // 成功静态方法，且有返回数据
    public static Result success(Object data) {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        result.data = data;
        return result;
    }

    // 失败静态方法
    public static Result error(String msg) {
        Result result = new Result();
        result.code = 0;
        result.msg = msg;
        return result;
    }
}
