package com.triminds.tlp.tracking;

import com.triminds.tlp.prediction.TrackingContext;
import com.triminds.tlp.tracking.repository.ShipmentTrackingSessionRepository;
import com.triminds.tlp.tracking.repository.TrackingEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackingEnrichmentService {

    private final ShipmentTrackingSessionRepository sessionRepository;
    private final TrackingEventRepository trackingEventRepository;

    /**
     * Constrói o contexto rico para predição combinando dados de GPS + RFID
     */
    public TrackingContext buildTrackingContext(String shipmentId, Long companyId) {
        ShipmentTrackingSession session = sessionRepository.findByShipmentId(shipmentId)
                .orElseThrow(() -> new IllegalArgumentException("Tracking session não encontrada para shipment: " + shipmentId));

        List<TrackingEvent> recentEvents = trackingEventRepository
                .findRecentEvents(companyId, shipmentId, LocalDateTime.now().minusHours(48));

        return TrackingContext.builder()
                .shipmentId(shipmentId)
                .vehicleId(session.getVehicleId())
                .recentEvents(recentEvents)
                .currentStatus(session.getCurrentStatus() != null ? session.getCurrentStatus() : "IN_TRANSIT")
                .origin("Origem não informada")           // TODO: Integrar com dados da Shipment
                .destination("Destino não informado")     // TODO: Integrar com dados da Shipment
                .build();
    }
}