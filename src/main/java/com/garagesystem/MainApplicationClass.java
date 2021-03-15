package com.garagesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MainApplicationClass {
    private static final Logger log = LoggerFactory.getLogger(MainApplicationClass.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApplicationClass.class, args);
    }
}
