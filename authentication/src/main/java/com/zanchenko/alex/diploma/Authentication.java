package com.zanchenko.alex.diploma;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@EnableFeignClients(value = "com.zanchenko.alex.diploma.client")
public class Authentication {

	public static void main(String[] args) {
		SpringApplication.run(Authentication.class, args);
	}

}