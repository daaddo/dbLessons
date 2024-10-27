package it.cascella.dbsetup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DbSetupApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbSetupApplication.class, args);
    }

}
