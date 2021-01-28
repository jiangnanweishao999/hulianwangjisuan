package com.example.bookreader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class BookreaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookreaderApplication.class, args);
    }

}
