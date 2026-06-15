package com.triminds.tlp.prediction.service;

import com.triminds.tlp.prediction.dto.PredictionRequestDTO;
import com.triminds.tlp.prediction.dto.PredictionResultDTO;
import com.triminds.tlp.prediction.engine.MLEngine;
import com.triminds.tlp.prediction.engine.PredictionEngine;
import com.triminds.tlp.prediction.entity.Prediction;
import com.triminds.tlp.prediction.repository.PredictionRepository;
import com.triminds.tlp.rfid.model.RfidTag;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionService {

    private final MLEngine mlEngine;
    private final PredictionEngine predictionEngine;
    private final PredictionRepository predictionRepository;

    public PredictionService(
            MLEngine mlEngine,
            PredictionEngine predictionEngine,
            PredictionRepository predictionRepository
    ) {
        this.mlEngine = mlEngine;
        this.predictionEngine = predictionEngine;
        this.predictionRepository = predictionRepository;
    }

    @Transactional
    public PredictionResult makePrediction(PredictionRequest request) {
        PredictionResult result = mlEngine.predictDemand(request.getContext());

        Prediction prediction = new Prediction();
        prediction.setCompanyId(request.getCompanyId());
        prediction.setPredictionType(request.getPredictionType());
        prediction.setInputFeatures(request.getContext());
        prediction.setConfidence(result.getConfidence());
        prediction.setResult(result.getPrediction());
        prediction.setCreatedAt(LocalDateTime.now());
        prediction.setValidUntil(LocalDateTime.now().plusHours(6));

        predictionRepository.save(prediction);

        return result;
    }

    public String predictNextLocation(RfidTag tag) {
        return predictionEngine.predict(tag).getPrediction();
    }

    public List<Prediction> getRecentPredictions(String companyId, int limit) {
        int safeLimit = Math.max(1, Math.min(limit, 500));

        return predictionRepository.findByCompanyIdOrderByCreatedAtDesc(
                companyId,
                PageRequest.of(0, safeLimit)
        );
    }
}
