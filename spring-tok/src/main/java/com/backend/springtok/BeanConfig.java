package com.backend.springtok;

import com.backend.springtok.entity.NewDoctor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan( basePackages = "com.backend.springtok.entity")
public class BeanConfig {

    @Bean
    public NewDoctor getNewDoctor() {
        return new NewDoctor();
    }

}

