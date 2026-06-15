package com.triminds.tlp.prediction.dto;

public class PredictionRequestDTO {

    private String companyId;
    private String predictionType;
    private String context;

    private String productId;
    private double demand;
    private int horizonDays;

    public PredictionRequestDTO() {
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getPredictionType() {
        return predictionType;
    }

    public String getContext() {
        return context;
    }

    public String getProductId() {
        return productId;
    }

    public double getDemand() {
        return demand;
    }

    public int getHorizonDays() {
        return horizonDays;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setPredictionType(String predictionType) {
        this.predictionType = predictionType;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setDemand(double demand) {
        this.demand = demand;
    }

    public void setHorizonDays(int horizonDays) {
        this.horizonDays = horizonDays;
    }
}
