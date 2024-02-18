package com.zanchenko.alex.diploma;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@OpenAPIDefinition(info = @Info(title = "Event Microservice API", version = "1.0", description = "Alex Zanchenko Diploma"))
public class EventMicroservice {
    public static void main(String[] args) {
        SpringApplication.run(EventMicroservice.class, args);
    }
}