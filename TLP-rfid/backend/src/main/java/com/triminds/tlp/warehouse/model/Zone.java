package com.triminds.tlp.warehouse.model;

import jakarta.persistence.*;

@Entity
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // RECEIVING, STORAGE, SHIPPING

    @ManyToOne
    private Warehouse warehouse;
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Warehouse getWarehouse() {
        return warehouse;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}