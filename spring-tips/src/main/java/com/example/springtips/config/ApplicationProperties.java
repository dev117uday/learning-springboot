package com.example.springtips.config;

import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@ConfigurationProperties(prefix = "app")
@ToString
@Validated
@ConstructorBinding
public class ApplicationProperties {

//    @Value("${version}")
    private final String version;

    private final FtpProperties ftp;

    public ApplicationProperties(String version, FtpProperties ftp) {
        this.version = version;
        this.ftp = ftp;
    }


    public String getVersion() {
        return version;
    }

    public FtpProperties getFtp() {
        return ftp;
    }

    @ToString
    @ConstructorBinding
    public static class FtpProperties {

        @NotEmpty
        private final String host;
        private final int port;
        private final String username;
        private final String password;

        public FtpProperties(String host, int port, String username, String password) {
            this.host = host;
            this.port = port;
            this.username = username;
            this.password = password;
        }

        public String getHost() {
            return host;
        }

        public int getPort() {
            return port;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

}
