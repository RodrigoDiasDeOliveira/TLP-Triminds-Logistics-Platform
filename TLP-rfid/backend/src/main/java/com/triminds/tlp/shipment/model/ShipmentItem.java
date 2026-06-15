package com.triminds.tlp.shipment.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "shipment_items")
public class ShipmentItem {

    @Id
    @GeneratedValue
    private UUID id;

    private String sku;

    private int quantity;

    @ManyToOne
    private Shipment shipment;

    public UUID getId() {
        return id;
    }
    public int getQuantity() {
        return quantity;
    }
    public Shipment getShipment() {
        return shipment;
    }
    public String getSku() {
        return sku;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
}