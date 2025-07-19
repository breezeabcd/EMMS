package com.wzydev.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supply {
    private Integer id;
    private String name;
    private Integer quantity;
    private String photo;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
