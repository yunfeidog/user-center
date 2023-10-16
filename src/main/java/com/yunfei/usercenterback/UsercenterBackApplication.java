package com.yunfei.usercenterback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yunfei.usercenterback.mapper")
public class UsercenterBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(UsercenterBackApplication.class, args);
    }

}
