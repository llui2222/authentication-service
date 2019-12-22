package com.tpam.service.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthenticationServiceApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }
}
