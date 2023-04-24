package one.udayyadav.springtips;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "appx")
@Validated
public record AppPropertiesRecord(String version, FtpProperties ftp) {

    public record FtpProperties(@NotEmpty String username) {
    }

}
