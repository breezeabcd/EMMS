package com.wzydev.controller;

import com.wzydev.pojo.*;
import com.wzydev.service.SupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/supplies")
@RestController
public class SupplyController {
    @Autowired
    private SupplyService supplyService;

    @GetMapping("/list")
    public Result findAll() {
        List<Supply> supplyList = supplyService.findAll();
        return Result.success(supplyList);
    }

    @GetMapping
    public Result page(SupplyQueryParam supplyQueryParam) {
        PageResult<Supply> pageResult = supplyService.page(supplyQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        supplyService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Supply supply) {
        supplyService.add(supply);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        Supply supply = supplyService.getById(id);
        return Result.success(supply);
    }

    @PutMapping
    public Result update(@RequestBody Supply supply) {
        supplyService.update(supply);
        return Result.success();
    }
}
