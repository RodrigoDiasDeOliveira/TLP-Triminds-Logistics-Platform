package com.triminds.tlp.tracking.repository;

import com.triminds.tlp.tracking.TrackingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TrackingEventRepository extends JpaRepository<TrackingEvent, UUID> {

    List<TrackingEvent> findByCompanyIdAndShipmentIdOrderByEventTimeDesc(Long companyId, String shipmentId);

    @Query("SELECT t FROM TrackingEvent t WHERE t.companyId = :companyId AND t.shipmentId = :shipmentId " +
           "AND t.eventTime >= :startTime ORDER BY t.eventTime DESC")
    List<TrackingEvent> findRecentEvents(Long companyId, String shipmentId, LocalDateTime startTime);
}