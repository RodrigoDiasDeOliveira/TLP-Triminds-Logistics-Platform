package com.triminds.tlp.analytics.dto;

public class PredictionKpiDTO {

    private double accuracy;
    private long predictions;
    private long correctPredictions;

    public PredictionKpiDTO() {
    }

    public PredictionKpiDTO(
            double accuracy,
            long predictions,
            long correctPredictions
    ) {
        this.accuracy = accuracy;
        this.predictions = predictions;
        this.correctPredictions = correctPredictions;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public long getPredictions() {
        return predictions;
    }

    public long getCorrectPredictions() {
        return correctPredictions;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public void setPredictions(long predictions) {
        this.predictions = predictions;
    }

    public void setCorrectPredictions(long correctPredictions) {
        this.correctPredictions = correctPredictions;
    }
}
