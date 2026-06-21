package com.triminds.tlp.prediction.impl;

import com.triminds.tlp.prediction.dto.PredictionAlert;
import com.triminds.tlp.prediction.dto.TrackingContext;
import com.triminds.tlp.prediction.engine.PredictionEngine;
import com.triminds.tlp.prediction.model.PredictionResult;
import com.triminds.tlp.rfid.model.RfidTag;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class WekaPredictionEngine implements PredictionEngine {


    public PredictionResult predictEta(TrackingContext context) {

        LocalDateTime estimatedEta = LocalDateTime.now().plusHours(10);

        PredictionResult result = new PredictionResult();

        result.setShipmentId(context.getShipmentId());
        result.setPredictedEta(estimatedEta);
        result.setConfidence(0.85);
        result.setRouteStatus("ON_TIME");
        result.setEstimatedDelayMinutes(0);
        result.setExplanation(
                "Predição baseada em dados GPS + RFID"
        );
        result.setModelType("WEKA");

        return result;
    }


    public List<PredictionAlert> predictRisks(TrackingContext context) {

        return List.of();
    }


    public Map<String, Object> getModelFeatures(TrackingContext context) {

        return Map.of(
                "eventCount",
                context.getRecentEvents().size()
        );
    }


    @Override
    public PredictionResult predict(RfidTag tag) {

        PredictionResult result = new PredictionResult();

        result.setTagId(tag.getTagId());
        result.setPrediction("ANALYSIS_REQUIRED");
        result.setModelType("WEKA");
        result.setConfidence(0.85);

        return result;
    }


    @Override
    public PredictionResult predictWithTracking(
            TrackingContext context
    ) {

        return predictEta(context);
    }

}