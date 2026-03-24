package com.example.demokangzhou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.demokangzhou.mapper")
@SpringBootApplication
public class DemoKangzhouApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoKangzhouApplication.class, args);
    }

}
