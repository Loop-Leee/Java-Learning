package com.lloop.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MyBatis Plus 学习示例主启动类
 */
@SpringBootApplication
@MapperScan("com.lloop.mybatisplus.mapper")
public class MybatisPlusLearningApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusLearningApplication.class, args);
    }
}

