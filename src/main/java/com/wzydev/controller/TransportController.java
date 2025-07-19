package com.wzydev.controller;

import com.wzydev.pojo.*;
import com.wzydev.service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/transports")
@RestController
public class TransportController {
    @Autowired
    private TransportService transportService;

    @GetMapping
    public Result page(TransportQueryParam transportQueryParam) {
        PageResult<Transport> pageResult = transportService.page(transportQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        transportService.delete(ids);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Transport transport) {
        transportService.add(transport);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Transport transport) {
        transportService.update(transport);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        Transport transport = transportService.getById(id);
        return Result.success(transport);
    }

}
