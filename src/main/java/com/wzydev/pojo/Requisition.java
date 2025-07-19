package com.wzydev.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Requisition {
    private Integer id;
    private Integer userId;
    private Integer supplyId;
    private Integer quantity;
    private Integer status = 0;
    private String opinion;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String applicantName;
    private String supplyName;
}
