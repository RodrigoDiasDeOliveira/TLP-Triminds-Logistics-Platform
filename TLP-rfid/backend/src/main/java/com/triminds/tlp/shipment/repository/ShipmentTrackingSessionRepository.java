package com.triminds.tlp.shipment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.triminds.tlp.tracking.ShipmentTrackingSession;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShipmentTrackingSessionRepository extends JpaRepository<ShipmentTrackingSession, UUID> {

    List<ShipmentTrackingSession> findByCompanyId(Long companyId);

    Optional<ShipmentTrackingSession> findByShipmentId(String shipmentId);

    List<ShipmentTrackingSession> findByVehicleIdAndEndTimeIsNull(String vehicleId);
}