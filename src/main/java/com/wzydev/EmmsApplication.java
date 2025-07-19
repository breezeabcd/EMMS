package com.wzydev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan  //开启SpringBoot的Servlet组件扫描
@SpringBootApplication
public class EmmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmmsApplication.class, args);
    }

}
