package com.triminds.tlp.shipment.model;

import com.triminds.tlp.company.model.Company;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue
    private UUID id;

    private String trackingCode;

    @ManyToOne
    private Company company;

    private String origin;

    private String destination;

    private LocalDateTime createdAt;

    public Company getCompany() {
        return company;
    }
     public LocalDateTime getCreatedAt() {
         return createdAt;
     }
     public String getDestination() {
         return destination;
     }
     public UUID getId() {
         return id;
     }
     public String getOrigin() {
         return origin;
     }
     public String getTrackingCode() {
         return trackingCode;
     }
     public void setCompany(Company company) {
         this.company = company;
     }
     public void setCreatedAt(LocalDateTime createdAt) {
         this.createdAt = createdAt;
     }
     public void setDestination(String destination) {
         this.destination = destination;
     }
     public void setId(UUID id) {
         this.id = id;
     }
     public void setOrigin(String origin) {
         this.origin = origin;
     }
     public void setTrackingCode(String trackingCode) {
         this.trackingCode = trackingCode;
     }

     }