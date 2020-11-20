package com.hr_manage;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 * 启动人力资源管理系统
 */

@SpringBootApplication
@MapperScan("com.hr_manage.mapper")
public class HrManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(HrManageApplication.class, args);
    }
}
