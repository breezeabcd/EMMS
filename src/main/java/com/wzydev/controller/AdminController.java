package com.wzydev.controller;

import com.wzydev.pojo.Admin;
import com.wzydev.pojo.Result;
import com.wzydev.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admins") //表示该类下所有方法的请求路径都以/admins开头
@RestController
public class AdminController {

    /**
     *借助 @Autowired 注解将 AdminService 注入进来，
     * 当调用 adminService.add(admin) 时，Spring 会依据依赖注入机制，
     * 实际调用的是 AdminServiceImpl 类里重写的方法。
     */
    @Autowired
    private AdminService adminService;

    @GetMapping
    public Result list() {
        List<Admin> adminList = adminService.findAll();
        return Result.success(adminList);
    }

    /**
     * 删除管理员-方式一：通过HttpServletRequest获取请求参数（不推荐，太繁琐）
     */
//    @DeleteMapping
//    public Result delete(HttpServletRequest request) {
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("根据id删除管理员：" + id);
//        return Result.success();
//    }

    /**
     * 删除管理员-方式二：通过@RequestParam注解获取请求参数。RequestParam从前端发送的请求中提取出id并绑定给形式参数adminId
     * 注意事项：一旦声明了@RequestParam，那么必须传递请求参数，否则会报错（因为required默认为true）
     * 如果想做到不传参也不报错，即《参数可选》，那么可以设置required为false，即在"id"后面加", required = false"
     */
//      @DeleteMapping
//      public Result delete(@RequestParam("id") Integer adminId) {
//          System.out.println("根据id删除管理员：" + adminId);
//          return Result.success();
//        }

    /**
     * 删除管理员-方式三：如果请求参数名与形参名相同，则@RequestParam注解可以省略（推荐）
     */
    @DeleteMapping
    public Result delete(Integer id) {
        adminService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增管理员
     */
    @PostMapping
    public Result add(@RequestBody Admin admin) {  // @RequestBody注解表示将请求体中的json数据转换为Admin对象
        adminService.add(admin);
        return Result.success();
    }

    /**
     * 根据id查询管理员
     */
//    @GetMapping("/{id}")  //{}里的id是路径参数，即URL中的变量
//    public Result getInfo(@PathVariable("id") Integer id) {// @PathVariable注解表示从URL中获取路径参数并赋给形参，其中value的值为URL中的变量名，如{id}
//        System.out.println("根据id查询管理员：" + id);
//        Admin admin = adminService.getInfo(id);
//        return Result.success(admin);
//    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {  //如果形参名和路径参数名相同，则"id"可省略
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    /**
     * 根据id修改管理员
     */
    @PutMapping
    public Result update(@RequestBody Admin admin) {
        adminService.update(admin);
        return Result.success();
    }
}
