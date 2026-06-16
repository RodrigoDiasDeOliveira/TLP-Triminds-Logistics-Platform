package com.triminds.tlp.shipment.repository;

import com.triminds.tlp.shipment.model.Shipment;
import com.triminds.tlp.shipment.model.ShipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
    List<Shipment> findByStatus(ShipmentStatus status);
    List<Shipment> findByCompanyId(Long companyId);
    long countByStatus(ShipmentStatus status);
}
