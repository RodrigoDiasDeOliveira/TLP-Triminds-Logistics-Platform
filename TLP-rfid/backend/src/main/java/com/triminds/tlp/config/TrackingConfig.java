package com.triminds.tlp.config;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.triminds.tlp.common.event.TrackingUpdatedEventPublisher;

@Configuration
@EnableScheduling
public class TrackingConfig {

    @Bean
    public TrackingUpdatedEventPublisher trackingEventPublisher(ApplicationEventPublisher publisher) {
        return new TrackingUpdatedEventPublisher(publisher);
    }
}