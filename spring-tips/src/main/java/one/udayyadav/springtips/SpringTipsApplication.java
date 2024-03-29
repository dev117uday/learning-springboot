package one.udayyadav.springtips;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({AppProperties.class, AppPropertiesRecord.class})
@Slf4j
public class SpringTipsApplication implements CommandLineRunner {

    @Value("${app.version}")
    private String appVersion;

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private AppPropertiesRecord appPropertiesRecord;

    public static void main(String[] args) {
        SpringApplication.run(SpringTipsApplication.class, args);
    }

    @Override
    public void run(String... args) {

        log.debug("something like this is possible when level.root is set to debug");

        System.out.println("app version : " + appVersion);
        System.out.println("app prop : " + appProperties.getVersion());
        System.out.println("app ftp user :  " + appProperties.getFtp().getUsername());
        System.out.println("app record :: app ftp user || " + appPropertiesRecord.version() + " :: " + appPropertiesRecord.ftp().username());
    }
}
