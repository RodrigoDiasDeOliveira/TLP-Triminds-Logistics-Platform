package com.triminds.tlp.analytics.dto;

public class ShipmentKpiDTO {

    private long totalShipments;
    private long deliveredShipments;
    private long delayedShipments;

    public ShipmentKpiDTO() {
    }

    public ShipmentKpiDTO(
            long totalShipments,
            long deliveredShipments,
            long delayedShipments
    ) {
        this.totalShipments = totalShipments;
        this.deliveredShipments = deliveredShipments;
        this.delayedShipments = delayedShipments;
    }

    public long getTotalShipments() {
        return totalShipments;
    }

    public long getDeliveredShipments() {
        return deliveredShipments;
    }

    public long getDelayedShipments() {
        return delayedShipments;
    }

    public void setTotalShipments(long totalShipments) {
        this.totalShipments = totalShipments;
    }

    public void setDeliveredShipments(long deliveredShipments) {
        this.deliveredShipments = deliveredShipments;
    }

    public void setDelayedShipments(long delayedShipments) {
        this.delayedShipments = delayedShipments;
    }
}
