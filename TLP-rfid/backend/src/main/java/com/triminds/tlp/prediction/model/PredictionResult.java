package com.triminds.tlp.prediction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PredictionResult {
    private String productId;
    private double predictedValue;
    private double confidence;
    private String modelType;
}