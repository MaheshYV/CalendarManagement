package com.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
 
@SpringBootApplication
@EntityScan(
	    basePackageClasses = { RestServiceApplication.class, Jsr310JpaConverters.class }
	)
public class RestServiceApplication  {
 
    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication .class, args);
    }
}