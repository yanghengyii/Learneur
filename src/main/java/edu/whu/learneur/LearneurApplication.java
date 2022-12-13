package edu.whu.learneur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LearneurApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearneurApplication.class, args);
    }

}
