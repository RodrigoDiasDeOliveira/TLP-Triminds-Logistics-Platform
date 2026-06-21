package com.triminds.tlp.common.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TrackingUpdatedEvent extends ApplicationEvent {

    private final String shipmentId;
    private final Long companyId;
    private final String status;

    public TrackingUpdatedEvent(Object source, String shipmentId, Long companyId, String status) {
        super(source);
        this.shipmentId = shipmentId;
        this.companyId = companyId;
        this.status = status;
    }
}