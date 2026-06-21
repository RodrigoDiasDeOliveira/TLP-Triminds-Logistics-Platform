package com.triminds.tlp.prediction.dto;

import com.triminds.tlp.tracking.TrackingEvent;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TrackingContext {

    private final String shipmentId;
    private final String vehicleId;
    private final String tagId;                    // compatibilidade com RFID existente
    private final List<TrackingEvent> recentEvents; // novos dados de GPS
    private final String currentStatus;
    private final String origin;
    private final String destination;

    // Mantém compatibilidade com predições antigas
    public String getContext() {
        return "shipment:" + shipmentId + "|vehicle:" + vehicleId;
    }
}