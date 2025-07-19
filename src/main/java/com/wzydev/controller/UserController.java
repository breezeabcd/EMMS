package com.wzydev.controller;

import com.wzydev.pojo.PageResult;
import com.wzydev.pojo.Result;
import com.wzydev.pojo.User;
import com.wzydev.pojo.UserQueryParam;
import com.wzydev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public Result findAll() {
        List<User> userList = userService.findAll();
        return Result.success(userList);
    }

    /**
     * 分页条件查询
     */
    /*@GetMapping
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer gender) {
        PageResult<User> pageResult = userService.page(page, pageSize, name, gender);
        return Result.success(pageResult);
    }*/

    /**
     * 分页条件查询（使用请求参数类）
     */
    @GetMapping
    public Result page(UserQueryParam userQueryParam) {
        PageResult<User> pageResult = userService.page(userQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增用户及其工作经历
     */
    @PostMapping
    public Result save(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    /**
     * 删除用户-数组
     */
    /*@DeleteMapping
    public Result delete(Integer[] ids) {
        return Result.success();
    }*/

    /**
     * 删除用户-List
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        userService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return Result
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        User user = userService.getInfo(id);
        return Result.success(user);
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @PutMapping
    public Result update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }
}
