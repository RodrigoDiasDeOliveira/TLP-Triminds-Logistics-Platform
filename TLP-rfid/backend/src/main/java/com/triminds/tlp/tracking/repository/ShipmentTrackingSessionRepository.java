package com.triminds.tlp.tracking.repository;

import com.triminds.tlp.tracking.ShipmentTrackingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShipmentTrackingSessionRepository extends JpaRepository<ShipmentTrackingSession, UUID> {

    Optional<ShipmentTrackingSession> findByShipmentId(String shipmentId);
}