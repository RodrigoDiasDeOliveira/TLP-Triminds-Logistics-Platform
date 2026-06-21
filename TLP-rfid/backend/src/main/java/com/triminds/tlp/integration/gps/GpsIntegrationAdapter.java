package com.triminds.tlp.integration.gps;

import com.triminds.tlp.common.domain.GpsCoordinate;
import com.triminds.tlp.common.domain.TrackingEventType;
import com.triminds.tlp.common.domain.VehicleSpeed;
import com.triminds.tlp.config.GpsProperties;
import com.triminds.tlp.tracking.TrackingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class GpsIntegrationAdapter {

    private final WebClient gpsWebClient;
    private final GpsProperties gpsProperties;

    public Mono<TrackingEvent> fetchCurrentPosition(String vehicleId, Long companyId) {
        return gpsWebClient.get()
                .uri("/vehicles/{vehicleId}/location", vehicleId)
                .retrieve()
                .bodyToMono(GpsPositionResponse.class)
                .map(response -> buildTrackingEvent(vehicleId, companyId, response))
                .doOnError(e -> log.error("Erro ao buscar GPS para veículo {}", vehicleId, e));
    }

    private TrackingEvent buildTrackingEvent(String vehicleId, Long companyId, GpsPositionResponse response) {
        return TrackingEvent.builder()
                .id(UUID.randomUUID())
                .companyId(companyId)
                .vehicleId(vehicleId)
                .eventType(TrackingEventType.GPS_UPDATE)
                .coordinate(GpsCoordinate.of(response.latitude(), response.longitude()))
                .speed(VehicleSpeed.kmh(response.speed()))
                .locationDescription(response.locationName())
                .eventTime(LocalDateTime.now())
                .build();
    }

    // Record (acesso via método sem "get")
    private record GpsPositionResponse(
            double latitude,
            double longitude,
            double speed,
            String locationName) {}
}