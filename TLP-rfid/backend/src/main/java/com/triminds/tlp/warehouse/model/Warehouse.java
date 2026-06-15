package com.triminds.tlp.warehouse.model;

import com.triminds.tlp.company.model.Company;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "warehouses")
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    @ManyToOne
    private Company company;

    public Company getCompany() {
        return company;
    }
    public Long getId() {
        return id;
    }
    public String getLocation() {
        return location;
    }
    public String getName() {
        return name;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setName(String name) {
        this.name = name;
    }
}