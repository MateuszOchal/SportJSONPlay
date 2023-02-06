package com.example;

import com.example.games.SportEventService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SportJsonPlayApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SportJsonPlayApplication.class, args);
        Menu menu = new Menu();
        Thread.sleep(1000);
        menu.showMenu();
    }
}
