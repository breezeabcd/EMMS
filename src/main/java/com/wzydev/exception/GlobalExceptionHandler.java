package com.wzydev.exception;

import com.wzydev.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler
//    public Result handleException(Exception e) {
//        return Result.error("出错了，请联系管理员");
//    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在");
    }
}
