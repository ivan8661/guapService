package com.schedguap.schedguap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;




@SpringBootApplication
@EnableEurekaClient
public class SchedguapApplication {


    public static Long SYNC_TIME = System.currentTimeMillis()/1000;
    public static String CURRENT_WEEK = "odd";

    static final Logger log =  LoggerFactory.getLogger(SchedguapApplication.class);

    public static Logger getLog() {
        return log;
    }


    public static void main(String[] args) {
        SpringApplication.run(SchedguapApplication.class, args);

    }

}
