package com.ozer.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {
//        org.springframework.boot.autoconfigure.security.SecurityProperties.class,
//        org.springframework.boot.autoconfigure.EnableAutoConfiguration.class})
@SpringBootApplication
public class BookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }

}
