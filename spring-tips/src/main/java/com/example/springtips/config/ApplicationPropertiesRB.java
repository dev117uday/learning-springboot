package com.example.springtips.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@ConfigurationProperties(prefix = "app")
public record ApplicationPropertiesRB(String version, FtpProperties ftp) {
    public record FtpProperties(
            @NotEmpty String host,
            @Positive int port,
            @NotEmpty String username,
            @NotEmpty String password
    ) {
    }
}