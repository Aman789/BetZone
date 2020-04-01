package com.bettingzone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
public class BetzoneApplication{
    public static void main(String[] args) throws Exception{

        SpringApplication.run(BetzoneApplication.class, args);


    }

}
