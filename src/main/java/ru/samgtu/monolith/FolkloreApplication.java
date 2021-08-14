package ru.samgtu.monolith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Creation date: 08.08.2021
 *
 * @author rassafel
 * @version 1.0
 */
@SpringBootApplication
@EnableScheduling
public class FolkloreApplication {
    public static void main(String[] args) {
        SpringApplication.run(FolkloreApplication.class, args);
    }
}
