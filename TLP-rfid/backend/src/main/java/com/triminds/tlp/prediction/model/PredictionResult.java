package com.triminds.tlp.prediction.model;

import java.time.LocalDateTime;

public class PredictionResult {

    private String tagId;
    private String prediction;
    private String modelType;
    private double confidence;

    // novos campos para IA logística
    private String shipmentId;
    private LocalDateTime predictedEta;
    private String routeStatus;
    private Integer estimatedDelayMinutes;
    private String explanation;


    public PredictionResult() {
    }


    // mantém compatibilidade com RuleEngine e MLEngine
    public PredictionResult(
            String tagId,
            String prediction,
            String modelType,
            double confidence
    ) {
        this.tagId = tagId;
        this.prediction = prediction;
        this.modelType = modelType;
        this.confidence = confidence;
    }


    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }


    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }


    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }


    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }


    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }


    public LocalDateTime getPredictedEta() {
        return predictedEta;
    }

    public void setPredictedEta(LocalDateTime predictedEta) {
        this.predictedEta = predictedEta;
    }


    public String getRouteStatus() {
        return routeStatus;
    }

    public void setRouteStatus(String routeStatus) {
        this.routeStatus = routeStatus;
    }


    public Integer getEstimatedDelayMinutes() {
        return estimatedDelayMinutes;
    }

    public void setEstimatedDelayMinutes(Integer estimatedDelayMinutes) {
        this.estimatedDelayMinutes = estimatedDelayMinutes;
    }


    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}