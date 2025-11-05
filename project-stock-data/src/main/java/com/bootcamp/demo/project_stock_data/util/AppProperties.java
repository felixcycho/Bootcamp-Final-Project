package com.bootcamp.demo.project_stock_data.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import lombok.Data;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "app")
public class AppProperties {
  private final Upload upload = new Upload();
    private final Provider provider = new Provider();

    @Data
    public static class Upload {
        // @NotBlank
        private String dir;  // app.upload.dir
    }

    @Data
    public static class Provider {
        // @NotBlank
        private String baseUrl;   // app.provider.base-url
        // @NotBlank
        private String apiToken;  // app.provider.api-token
    }
}
