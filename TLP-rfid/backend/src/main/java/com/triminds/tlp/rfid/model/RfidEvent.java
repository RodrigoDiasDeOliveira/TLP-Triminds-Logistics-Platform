package com.triminds.tlp.rfid.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rfid_events")
public class RfidEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tagId;

    private String readerId;

    private LocalDateTime timestamp = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventType eventType = EventType.SCAN;

    private String location;

    private String productCode;

    private String companyId;

    public RfidEvent() {
    }

    public RfidEvent(String tagId, String location, EventType eventType) {
        this.tagId = tagId;
        this.location = location;
        this.eventType = eventType;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
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
}
