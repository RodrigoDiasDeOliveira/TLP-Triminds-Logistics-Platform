package com.triminds.tlp.prediction.engine;

import com.triminds.tlp.prediction.model.PredictionResult;
import com.triminds.tlp.rfid.model.RfidTag;
import org.springframework.stereotype.Service;

@Service
public class PredictionEngine {

    private final RuleEngine ruleEngine;
    private final HistoricalEngine historicalEngine;
    private final MLEngine mlEngine;

    public PredictionEngine(
            RuleEngine ruleEngine,
            HistoricalEngine historicalEngine,
            MLEngine mlEngine
    ) {
        this.ruleEngine = ruleEngine;
        this.historicalEngine = historicalEngine;
        this.mlEngine = mlEngine;
    }

    public PredictionResult predict(RfidTag tag) {
        if (hasMLCapability(tag)) {
            return mlEngine.predict(tag);
        }

        if (hasHistory(tag)) {
            return historicalEngine.predict(tag);
        }

        return ruleEngine.predict(tag);
    }

    private boolean hasMLCapability(RfidTag tag) {
        return false;
    }

    private boolean hasHistory(RfidTag tag) {
        return true;
    }
}
