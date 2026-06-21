package com.triminds.tlp.prediction.service;

import com.triminds.tlp.common.event.TrackingUpdatedEvent;
import com.triminds.tlp.prediction.dto.PredictionRequestDTO;
import com.triminds.tlp.prediction.dto.PredictionResultDTO;
import com.triminds.tlp.prediction.dto.TrackingContext;
import com.triminds.tlp.prediction.engine.PredictionEngine;
import com.triminds.tlp.prediction.entity.Prediction;
import com.triminds.tlp.prediction.model.PredictionResult;
import com.triminds.tlp.prediction.repository.PredictionRepository;
import com.triminds.tlp.rfid.model.RfidTag;
import com.triminds.tlp.tracking.TrackingEnrichmentService;
import com.triminds.tlp.prediction.engine.MLEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PredictionService {

    private final PredictionEngine predictionEngine;
    private final TrackingEnrichmentService enrichmentService;   // Novo
    private final PredictionRepository predictionRepository;
    private final MLEngine mlEngine;

    // Método existente (mantido)
    @Transactional
    public PredictionResultDTO makePrediction(PredictionRequestDTO request) {
        PredictionResultDTO result = mlEngine.predictDemand(request.getContext());

        // ... (lógica existente mantida)
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

    // Método existente (mantido)
    public String predictNextLocation(RfidTag tag) {
        return predictionEngine.predict(tag).getPrediction();
    }

    // === NOVO MÉTODO PARA INTEGRAR COM GPS/TRACKING ===
    @EventListener
    public void handleTrackingUpdated(TrackingUpdatedEvent event) {
        try {
            TrackingContext context = enrichmentService.buildTrackingContext(
                    event.getShipmentId(), event.getCompanyId());

            PredictionResult result = predictionEngine.predictWithTracking(context);

            log.info("Predição com GPS realizada para shipment {} → {}", 
                    event.getShipmentId(), result.getPrediction());
            
            // Salvar predição enriquecida
        } catch (Exception e) {
            log.error("Erro na predição com tracking para shipment {}", event.getShipmentId(), e);
        }
    }

    public List<Prediction> getRecentPredictions(String companyId, int limit) {
        int safeLimit = Math.max(1, Math.min(limit, 500));
        return predictionRepository.findByCompanyIdOrderByCreatedAtDesc(
                companyId, PageRequest.of(0, safeLimit));
    }
}