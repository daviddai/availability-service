package com.micro.services.availability.svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.micro.services.availability.svc")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}