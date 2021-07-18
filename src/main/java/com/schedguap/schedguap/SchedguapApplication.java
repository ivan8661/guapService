package com.schedguap.schedguap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class SchedguapApplication {


    public static Long SYNC_TIME = System.currentTimeMillis()/1000;
    public static String CURRENT_WEEK = "odd";


    public static void main(String[] args) {
        SpringApplication.run(SchedguapApplication.class, args);

    }

}
