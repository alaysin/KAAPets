package org.levelup.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.levelup.model", "org.levelup.web"})
public class PetsBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetsBookingApplication.class, args);
    }
}
