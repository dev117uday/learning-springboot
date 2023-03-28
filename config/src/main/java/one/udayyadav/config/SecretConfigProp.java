package one.udayyadav.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("secret")
public record SecretConfigProp(String username, String password, String authToken) {
}

