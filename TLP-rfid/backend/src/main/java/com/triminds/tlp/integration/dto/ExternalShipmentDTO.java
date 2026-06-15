package com.triminds.tlp.integration.dto;

public class ExternalShipmentDTO {

    private String externalId;
    private String origin;
    private String destination;
    private String status;
    private String companyCode;

    public ExternalShipmentDTO() {
    }

    public String getExternalId() {
        return externalId;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getStatus() {
        return status;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
