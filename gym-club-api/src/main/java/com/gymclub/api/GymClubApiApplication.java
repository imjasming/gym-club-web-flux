package com.gymclub.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

//@ComponentScan(basePackages = "com.gymclub.core")
//@EnableJpaRepositories("com.gymclub.core.repository")
@EnableReactiveMongoRepositories
@EntityScan("com.gymclub.api.domain")
@SpringBootApplication
public class GymClubApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymClubApiApplication.class, args);
    }

}
