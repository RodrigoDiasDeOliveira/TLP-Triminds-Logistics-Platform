package com.triminds.tlp.integration.dto;

import java.time.LocalDateTime;

public class RfidEventDTO {

    private String tagId;
    private String location;
    private String action;
    private String item;
    private String companyId;
    private LocalDateTime timestamp;

    public RfidEventDTO() {
    }

    public String getTagId() {
        return tagId;
    }

    public String getLocation() {
        return location;
    }

    public String getAction() {
        return action;
    }

    public String getItem() {
        return item;
    }

    public String getCompanyId() {
        return companyId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
