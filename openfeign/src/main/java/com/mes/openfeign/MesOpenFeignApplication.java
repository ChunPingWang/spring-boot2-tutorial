package com.mes.openfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MesOpenFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(MesOpenFeignApplication.class, args);
    }
}
