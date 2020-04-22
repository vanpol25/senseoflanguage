package com.senseoflanguage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class SenseoflanguageApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(SenseoflanguageApplication.class, args);
    }

}
