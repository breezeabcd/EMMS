package com.wzydev.controller;

import com.wzydev.pojo.LoginInfo;
import com.wzydev.pojo.Result;
import com.wzydev.pojo.User;
import com.wzydev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        LoginInfo info = userService.login(user);
        if(info != null) {
            return Result.success(info);
        }
        return Result.error("用户名或密码错误");
    }
}
