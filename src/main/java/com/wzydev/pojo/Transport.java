package com.wzydev.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transport {
    private Integer id;
    private Integer supplyId;
    private String destination;
    private Integer quantity;
    private String detail;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String supplyName;
}
