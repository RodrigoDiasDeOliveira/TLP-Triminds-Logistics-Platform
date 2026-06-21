package com.triminds.tlp.gps;

import com.triminds.tlp.config.GpsProperties;
import com.triminds.tlp.integration.gps.GpsIntegrationAdapter;
import com.triminds.tlp.integration.gps.GpsEventPublisher;
import com.triminds.tlp.tracking.TrackingEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "gps.enabled", havingValue = "true")
public class GpsPollingScheduler {

    private final GpsIntegrationAdapter gpsAdapter;
    private final GpsEventPublisher eventPublisher;
    private final GpsProperties gpsProperties;

    // Lista de veículos ativos - em produção usar cache ou banco
    private final List<VehicleTrackingJob> activeVehicles = List.of(); // TODO: carregar dinamicamente

    @Scheduled(fixedRateString = "#{${gps.providers.generic.polling-interval-seconds} * 1000}")
    public void pollActiveVehicles() {
        if (!gpsProperties.isEnabled()) return;

        log.debug("Iniciando polling GPS para {} veículos", activeVehicles.size());

        activeVehicles.forEach(job -> {
            try {
                gpsAdapter.fetchCurrentPosition(job.vehicleId(), job.companyId())
                        .subscribe(event -> {
                            eventPublisher.publishGpsReceived(event);
                            log.info("GPS event publicado para veículo: {}", job.vehicleId());
                        });
            } catch (Exception e) {
                log.warn("Falha no polling do veículo {}", job.vehicleId(), e);
            }
        });
    }

    // Record auxiliar
    private record VehicleTrackingJob(String vehicleId, Long companyId) {}
}