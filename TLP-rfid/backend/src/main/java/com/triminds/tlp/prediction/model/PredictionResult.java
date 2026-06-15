package com.triminds.tlp.prediction.model;

public class PredictionResult {

    private String tagId;
    private String prediction;
    private String modelType;
    private double confidence;

    public PredictionResult() {
    }

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

    public String getPrediction() {
        return prediction;
    }

    public String getModelType() {
        return modelType;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
