package com.hjwblog.robo_cmp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hjwblog.robo_cmp.model.mapper")
public class RoboCmpApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoboCmpApplication.class, args);
    }

}
