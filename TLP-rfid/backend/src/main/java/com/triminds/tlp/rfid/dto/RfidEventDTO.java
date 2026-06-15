package com.triminds.tlp.rfid.dto;

import com.triminds.tlp.rfid.model.EventType;

import java.time.LocalDateTime;
import java.util.Map;

public class RfidEventDTO {

    private String tagId;
    private String readerId;
    private LocalDateTime timestamp;
    private EventType eventType;
    private String location;
    private String productCode;
    private String companyId;
    private Map<String, Object> metadata;

    public RfidEventDTO() {
    }

    public String getTagId() {
        return tagId;
    }

    public String getReaderId() {
        return readerId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public EventType getEventType() {
        return eventType;
    }

    public String getLocation() {
        return location;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getCompanyId() {
        return companyId;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}
