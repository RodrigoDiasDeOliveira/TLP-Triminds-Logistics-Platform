package com.triminds.tlp.integration.gps;

import com.triminds.tlp.common.event.GpsReceivedEvent;
import com.triminds.tlp.tracking.TrackingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GpsEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishGpsReceived(TrackingEvent trackingEvent) {
        GpsReceivedEvent event = new GpsReceivedEvent(this, trackingEvent);
        applicationEventPublisher.publishEvent(event);
        log.info("GPS Received Event publicado para shipment: {}", trackingEvent.getShipmentId());
    }
}