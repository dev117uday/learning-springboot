package com.example.springtips;

import com.example.springtips.config.ApplicationProperties;
import com.example.springtips.config.ApplicationPropertiesRB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class SpringTipsApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringTipsApplication.class);

    public static void main(String[] args) {

        logger.debug("------- hello world ------- ");

        SpringApplication.run(SpringTipsApplication.class, args);
    }

}

@Component
class AppInit implements CommandLineRunner {

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    private ApplicationProperties applicationProperties;

//    @Autowired
//    private ApplicationPropertiesRB applicationPropertiesRB;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("App Version : " + appVersion);
        System.out.println("ApplicationProperties version : " + applicationProperties.getVersion());
        System.out.println("ApplicationProperties : " + applicationProperties);
//        not working
//        System.out.println("Application Properties RB : " + applicationPropertiesRB);
    }
}
