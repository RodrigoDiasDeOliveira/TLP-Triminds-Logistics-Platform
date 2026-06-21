package com.triminds.tlp.common.event;

import com.triminds.tlp.tracking.TrackingEvent;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class GpsReceivedEvent extends ApplicationEvent {

    private final TrackingEvent trackingEvent;

    public GpsReceivedEvent(Object source, TrackingEvent trackingEvent) {
        super(source);
        this.trackingEvent = trackingEvent;
    }
}