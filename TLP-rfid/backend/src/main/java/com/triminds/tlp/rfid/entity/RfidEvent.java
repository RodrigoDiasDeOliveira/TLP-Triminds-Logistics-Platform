package com.triminds.tlp.rfid.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "rfid_events")
public class RfidEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagId;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private String location;

    public EventType getEventType() {
        return eventType;
    }
    public Long getId() {
        return id;
    }
    public String getLocation() {
        return location;
    }
    public String getTagId() {
        return tagId;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setTagId(String tagId) {
        this.tagId = tagId;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}