package one.udayyadav.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ConfigRunner implements ApplicationRunner {

    private final SecretConfigProp secretConfigProp;

    @Value("${welcome.value:Default}")
    private String greetingValue;

    @Value("${welcome.msg}")
    private String welcomeMsg;


    public ConfigRunner(SecretConfigProp secretConfigProp) {
        this.secretConfigProp = secretConfigProp;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("XXXXXXXXXXX ============ XXXXXXXXXXXXXX");
        log.info("XXXXXXXXXXX " + greetingValue + " XXXXXXXXXXXXXX");
        log.info("XXXXXXXXXXX " + welcomeMsg + " XXXXXXXXXXXXXX");
        log.info("XXXXXXXXXXX " + secretConfigProp.username() + " XXXXXXXXXXXXXX");
        log.info("XXXXXXXXXXX " + secretConfigProp.authToken() + " XXXXXXXXXXXXXX");
        log.info("XXXXXXXXXXX " + secretConfigProp.password() + " XXXXXXXXXXXXXX");
    }
    
}
