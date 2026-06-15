package com.triminds.tlp.prediction.engine;

import com.triminds.tlp.rfid.model.RfidTag;
import org.springframework.stereotype.Service;

@Service
public class MLEngine {

    public com.triminds.tlp.prediction.dto.PredictionResultDTO predictDemand(String context) {
        double confidence = 0.75;

        String prediction = context == null || context.isBlank()
                ? "DEMAND_STABLE"
                : "DEMAND_ANALYZED";

        return new com.triminds.tlp.prediction.dto.PredictionResultDTO(
                prediction,
                confidence
        );
    }

    public com.triminds.tlp.prediction.model.PredictionResult predict(
            String entityId,
            String context
    ) {
        double score = Math.random();

        String prediction;

        if (score > 0.8) {
            prediction = "HIGH_ACTIVITY";
        } else if (score > 0.5) {
            prediction = "MEDIUM_ACTIVITY";
        } else {
            prediction = "LOW_ACTIVITY";
        }

        return new com.triminds.tlp.prediction.model.PredictionResult(
                entityId,
                prediction,
                context,
                score
        );
    }

    public com.triminds.tlp.prediction.model.PredictionResult predict(RfidTag tag) {
        return new com.triminds.tlp.prediction.model.PredictionResult(
                tag.getTagId(),
                "analysis-required",
                "ML",
                0.70
        );
    }
}
