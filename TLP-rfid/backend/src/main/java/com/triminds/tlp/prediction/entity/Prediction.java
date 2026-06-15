package com.triminds.tlp.prediction.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "predictions")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyId;

    private String predictionType;

    @Column(columnDefinition = "TEXT")
    private String inputFeatures;

    private Double confidence;

    private String result;

    private LocalDateTime createdAt;

    private LocalDateTime validUntil;

    public Prediction() {
    }

    public Long getId() {
        return id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getPredictionType() {
        return predictionType;
    }

    public String getInputFeatures() {
        return inputFeatures;
    }

    public Double getConfidence() {
        return confidence;
    }

    public String getResult() {
        return result;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setPredictionType(String predictionType) {
        this.predictionType = predictionType;
    }

    public void setInputFeatures(String inputFeatures) {
        this.inputFeatures = inputFeatures;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
