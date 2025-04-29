package com.dutra.ordertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
//@EnableScheduling
public class OrdertestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdertestApplication.class, args);
    }

}
