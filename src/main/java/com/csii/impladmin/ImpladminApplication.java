package com.csii.impladmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ImpladminApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImpladminApplication.class, args);
    }

}
