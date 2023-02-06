package com.example;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SportJsonPlayApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SportJsonPlayApplication.class, args);
        Menu menu = new Menu();
        Thread.sleep(1000);
        menu.showMenu();
    }
}
