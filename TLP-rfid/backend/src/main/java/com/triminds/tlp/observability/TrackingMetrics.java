package com.triminds.tlp.observability;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TrackingMetrics {

    private final MeterRegistry registry;

    public void recordGpsEvent() {
        registry.counter("tlp.tracking.gps.events").increment();
    }

    public void recordPrediction(String status) {
        registry.counter("tlp.prediction.invocations", "status", status).increment();
    }
}