package com.triminds.tlp.analytics.service;

import com.triminds.tlp.analytics.dto.PredictionKpiDTO;
import com.triminds.tlp.prediction.engine.HistoricalEngine;
import org.springframework.stereotype.Service;

@Service
public class PredictionAccuracyService {

    private final HistoricalEngine historicalEngine;

    public PredictionAccuracyService(HistoricalEngine historicalEngine) {
        this.historicalEngine = historicalEngine;
    }

    public PredictionKpiDTO calculateAccuracy() {
        long events = historicalEngine.countEventsLast24h();
        long shipments = historicalEngine.countShipments();

        if (events == 0) {
            return new PredictionKpiDTO(0, 0, 0);
        }

        double accuracy = (double) shipments / events;

        return new PredictionKpiDTO(
                accuracy,
                events,
                Math.min(events, shipments)
        );
    }
}
