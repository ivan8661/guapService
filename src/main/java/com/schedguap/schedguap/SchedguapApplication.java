package com.schedguap.schedguap;

import com.schedguap.schedguap.Services.DataImport.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class SchedguapApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedguapApplication.class, args);

    }

}
