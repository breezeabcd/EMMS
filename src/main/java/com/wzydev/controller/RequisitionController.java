package com.wzydev.controller;

import com.wzydev.pojo.*;
import com.wzydev.service.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/requisitions")
@RestController
public class RequisitionController {
    @Autowired
    private RequisitionService requisitionService;

    @GetMapping
    public Result page(RequisitionQueryParam requisitionQueryParam) {
        PageResult<Requisition> pageResult = requisitionService.page(requisitionQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        requisitionService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Requisition requisition) {
        requisitionService.add(requisition);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        Requisition requisition = requisitionService.getInfo(id);
        return Result.success(requisition);
    }

    @PutMapping
    public Result update(@RequestBody Requisition requisition) {
        requisitionService.update(requisition);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result approve(@PathVariable Integer id) {
        String errorMsg = requisitionService.approve(id);
        if(errorMsg == null) return Result.success();
        return Result.error(errorMsg);
    }
}
