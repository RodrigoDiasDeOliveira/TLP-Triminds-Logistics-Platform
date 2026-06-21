package com.triminds.tlp.tracking;

import com.triminds.tlp.common.event.GpsReceivedEvent;
import com.triminds.tlp.common.event.TrackingUpdatedEvent;
import com.triminds.tlp.tracking.repository.ShipmentTrackingSessionRepository;
import com.triminds.tlp.tracking.repository.TrackingEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackingService {

    private final TrackingEventRepository trackingEventRepository;
    private final ShipmentTrackingSessionRepository sessionRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void processTrackingEvent(TrackingEvent event) {
        trackingEventRepository.save(event);

        ShipmentTrackingSession session = sessionRepository.findByShipmentId(event.getShipmentId())
                .orElseGet(() -> createNewSession(event));

        session.addTrackingEvent(event);
        sessionRepository.save(session);

        // Publica evento para Prediction
        eventPublisher.publishEvent(new TrackingUpdatedEvent(this, 
            event.getShipmentId(), event.getCompanyId(), session.getCurrentStatus()));
    }

    private ShipmentTrackingSession createNewSession(TrackingEvent event) {
        return ShipmentTrackingSession.builder()
                .companyId(event.getCompanyId())
                .shipmentId(event.getShipmentId())
                .vehicleId(event.getVehicleId())
                .startTime(LocalDateTime.now())
                .currentStatus("IN_TRANSIT")
                .build();
    }

    @EventListener
    public void handleGpsReceived(GpsReceivedEvent event) {
        processTrackingEvent(event.getTrackingEvent());
    }

    public List<TrackingEvent> getTrackingHistory(String shipmentId, Long companyId) {
        return trackingEventRepository.findByCompanyIdAndShipmentIdOrderByEventTimeDesc(companyId, shipmentId);
    }
}