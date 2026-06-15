package com.triminds.tlp.prediction.dto;

public class PredictionResultDTO {

    private String prediction;
    private double confidence;

    public PredictionResultDTO() {
    }

    public PredictionResultDTO(String prediction, double confidence) {
        this.prediction = prediction;
        this.confidence = confidence;
    }

    public String getPrediction() {
        return prediction;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
