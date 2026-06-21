package com.triminds.tlp.interfaces.rest.gps;

import com.triminds.tlp.prediction.PredictionResult;
import com.triminds.tlp.tracking.TrackingEvent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TrackingResponse {

    private String shipmentId;
    private String vehicleId;
    private String currentStatus;
    private LocalDateTime lastUpdate;
    private PredictionResult prediction;
    private List<TrackingEvent> recentEvents;

    public static TrackingResponse fromEvent(TrackingEvent event) {
        return TrackingResponse.builder()
                .shipmentId(event.getShipmentId())
                .vehicleId(event.getVehicleId())
                .currentStatus("IN_TRANSIT")
                .lastUpdate(event.getEventTime())
                .recentEvents(List.of(event))
                .build();
    }
}