package com.triminds.tlp.analytics.dto;

public class DashboardDTO {

    private WarehouseKpiDTO warehouse;
    private ShipmentKpiDTO shipment;
    private PredictionKpiDTO prediction;

    public DashboardDTO() {
    }

    public DashboardDTO(
            WarehouseKpiDTO warehouse,
            ShipmentKpiDTO shipment,
            PredictionKpiDTO prediction
    ) {
        this.warehouse = warehouse;
        this.shipment = shipment;
        this.prediction = prediction;
    }

    public WarehouseKpiDTO getWarehouse() {
        return warehouse;
    }

    public ShipmentKpiDTO getShipment() {
        return shipment;
    }

    public PredictionKpiDTO getPrediction() {
        return prediction;
    }

    public void setWarehouse(WarehouseKpiDTO warehouse) {
        this.warehouse = warehouse;
    }

    public void setShipment(ShipmentKpiDTO shipment) {
        this.shipment = shipment;
    }

    public void setPrediction(PredictionKpiDTO prediction) {
        this.prediction = prediction;
    }
}
