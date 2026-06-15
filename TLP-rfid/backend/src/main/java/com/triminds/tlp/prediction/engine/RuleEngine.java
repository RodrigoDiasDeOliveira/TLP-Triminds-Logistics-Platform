package com.triminds.tlp.prediction.engine;

import com.triminds.tlp.prediction.model.PredictionResult;
import com.triminds.tlp.rfid.model.RfidTag;
import org.springframework.stereotype.Component;

@Component
public class RuleEngine {

    public PredictionResult predict(RfidTag tag) {
        String current = tag.getLocation();

        String next;

        if ("warehouse-a".equalsIgnoreCase(current)) {
            next = "warehouse-b";
        } else if ("warehouse-b".equalsIgnoreCase(current)) {
            next = "shipping-zone";
        } else if ("shipping-zone".equalsIgnoreCase(current)) {
            next = "in-transit";
        } else {
            next = "analysis-required";
        }

        return new PredictionResult(
                tag.getTagId(),
                next,
                "RULE",
                0.60
        );
    }
}
