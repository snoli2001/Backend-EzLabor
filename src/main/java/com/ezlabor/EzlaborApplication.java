package com.ezlabor;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EzlaborApplication {

    public static void main(String[] args) {
        SpringApplication.run(EzlaborApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper(){ return new ModelMapper();}
}
