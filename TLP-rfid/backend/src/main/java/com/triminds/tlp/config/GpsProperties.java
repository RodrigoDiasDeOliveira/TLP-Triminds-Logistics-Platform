package com.triminds.tlp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties(prefix = "gps")
public class GpsProperties {

    private boolean enabled = true;
    private String defaultProvider = "generic";
    private Map<String, ProviderConfig> providers = new HashMap<>();

    @Data
    public static class ProviderConfig {
        private String baseUrl;
        private String apiKey;
        private int pollingIntervalSeconds = 30;
        private int timeoutSeconds = 10;
    }
}