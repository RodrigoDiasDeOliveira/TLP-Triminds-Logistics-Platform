package com.triminds.tlp.shipment.repository;

import com.triminds.tlp.shipment.model.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID> {

    List<Shipment> findByStatus(String status);

    List<Shipment> findByCompanyId(UUID companyId);
}