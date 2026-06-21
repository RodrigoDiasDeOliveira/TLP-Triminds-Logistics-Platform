package com.triminds.tlp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class TrackingConfig {

    @Bean
    public TrackingUpdatedEventPublisher trackingEventPublisher(ApplicationEventPublisher publisher) {
        return new TrackingUpdatedEventPublisher(publisher);
    }
}