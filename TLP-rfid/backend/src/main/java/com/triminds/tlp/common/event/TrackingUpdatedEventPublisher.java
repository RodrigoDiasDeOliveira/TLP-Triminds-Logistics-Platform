package com.triminds.tlp.common.event;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrackingUpdatedEventPublisher {

    private final ApplicationEventPublisher publisher;

    public void publish(TrackingUpdatedEvent event) {
        publisher.publishEvent(event);
    }
}