package com.triminds.tlp.interfaces.rest;

import com.triminds.tlp.integration.gps.GpsIntegrationAdapter;
import com.triminds.tlp.interfaces.TrackingResponse;
import com.triminds.tlp.interfaces.rest.gps.GpsTrackingRequest;
import com.triminds.tlp.tracking.TrackingEnrichmentService;
import com.triminds.tlp.tracking.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/gps")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('LOGISTICS_OPERATOR', 'ADMIN', 'USER')")
public class GpsController {

    private final GpsIntegrationAdapter gpsAdapter;
    private final TrackingService trackingService;
    private final TrackingEnrichmentService enrichmentService;

    /**
     * Força a busca imediata da posição GPS de um veículo
     */
    @PostMapping("/track")
    public Mono<ResponseEntity<TrackingResponse>> trackVehicle(@RequestBody GpsTrackingRequest request) {
        return gpsAdapter.fetchCurrentPosition(request.getVehicleId(), request.getCompanyId())
                .map(event -> {
                    trackingService.processTrackingEvent(event);
                    return ResponseEntity.ok(TrackingResponse.fromEvent(event));
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    /**
     * Retorna o histórico completo de tracking + informações enriquecidas
     */
    @GetMapping("/tracking/{shipmentId}")
    public ResponseEntity<TrackingResponse> getTracking(
            @PathVariable String shipmentId,
            @RequestParam Long companyId) {

        var events = trackingService.getTrackingHistory(shipmentId, companyId);

        TrackingResponse response = TrackingResponse.builder()
                .shipmentId(shipmentId)
                .vehicleId(events.isEmpty() ? null : events.get(0).getVehicleId())
                .currentStatus(events.isEmpty() ? "UNKNOWN" : "IN_TRANSIT")
                .lastUpdate(events.isEmpty() ? null : events.get(0).getEventTime())
                .recentEvents(events)
                .build();

        return ResponseEntity.ok(response);
    }
}