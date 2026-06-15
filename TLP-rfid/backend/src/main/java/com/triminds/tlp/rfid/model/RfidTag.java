package com.triminds.tlp.rfid.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name = "rfid_tags")
public class RfidTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    private String tagId;

    private String productName;

    private String location;

    private LocalDateTime lastScanned;

    public RfidTag() {
    }

    public RfidTag(Long id, String tagId, String productName, String location, LocalDateTime lastScanned) {
        this.id = id;
        this.tagId = tagId;
        this.productName = productName;
        this.location = location;
        this.lastScanned = lastScanned;
    }

    public Long getId() {
        return id;
    }

    public String getTagId() {
        return tagId;
    }

    public String getProductName() {
        return productName;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getLastScanned() {
        return lastScanned;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLastScanned(LocalDateTime lastScanned) {
        this.lastScanned = lastScanned;
    }
}
