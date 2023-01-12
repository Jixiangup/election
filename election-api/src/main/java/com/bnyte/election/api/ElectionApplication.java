package com.bnyte.election.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author bnyte
 * @since 1.0.0
 */
@EnableAsync
@SpringBootApplication
public class ElectionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ElectionApplication.class, args);
    }
}
