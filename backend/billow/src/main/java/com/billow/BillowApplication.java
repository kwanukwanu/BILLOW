package com.billow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@EnableScheduling
@SpringBootApplication
public class BillowApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BillowApplication.class, args);
    }
}