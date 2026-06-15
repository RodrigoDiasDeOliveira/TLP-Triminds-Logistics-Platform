package com.triminds.tlp.analytics.dto;

public class WarehouseKpiDTO {

    private String warehouseName;
    private long totalMovements;
    private double throughput;
    private double occupancyRate;

    public WarehouseKpiDTO() {
    }

    public WarehouseKpiDTO(
            String warehouseName,
            long totalMovements,
            double throughput,
            double occupancyRate
    ) {
        this.warehouseName = warehouseName;
        this.totalMovements = totalMovements;
        this.throughput = throughput;
        this.occupancyRate = occupancyRate;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public long getTotalMovements() {
        return totalMovements;
    }

    public double getThroughput() {
        return throughput;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public void setTotalMovements(long totalMovements) {
        this.totalMovements = totalMovements;
    }

    public void setThroughput(double throughput) {
        this.throughput = throughput;
    }

    public void setOccupancyRate(double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }
}
