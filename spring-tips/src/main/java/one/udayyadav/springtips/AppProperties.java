package one.udayyadav.springtips;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "app")
@Getter
//@Setter
@Validated
@RequiredArgsConstructor
public class AppProperties {
    private final String version;
    private final FtpProperties ftp;

    @Getter
    @Setter
    public static class FtpProperties {
        @NotEmpty
        private String username;
    }

}
