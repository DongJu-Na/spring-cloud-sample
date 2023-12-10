package com.example;

import com.example.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DummyApiApplication implements CommandLineRunner {

    @Resource
    FileService fileService;

    public static void main(String[] args) {
        SpringApplication.run(DummyApiApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        fileService.init();
    }
}