package com.dutra.ordertest;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(
        info = @Info(
                title = "API orders",
                version = "1.0",
                description = "Documentação da API de gerenciamento de pedidos"
        )
)
public class OrdertestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdertestApplication.class, args);
    }

}
