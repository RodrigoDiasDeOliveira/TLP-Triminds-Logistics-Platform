package com.triminds.tlp.prediction.engine;

import com.triminds.tlp.prediction.dto.TrackingContext;
import com.triminds.tlp.prediction.model.PredictionResult;
import com.triminds.tlp.rfid.model.RfidTag;

public interface PredictionEngine {

    // Método existente (mantido)
    PredictionResult predict(RfidTag tag);

    // Novo método para suportar GPS + Tracking
    PredictionResult predictWithTracking(TrackingContext context);

}