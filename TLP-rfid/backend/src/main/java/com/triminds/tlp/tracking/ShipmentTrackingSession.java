package com.triminds.tlp.tracking;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "shipment_tracking_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentTrackingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long companyId;

    private String shipmentId;
    private String vehicleId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tracking_session_id")
    @Builder.Default
    private List<TrackingEvent> events = new ArrayList<>();

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String currentStatus;
    private String predictedEta;

    public void addTrackingEvent(TrackingEvent event) {
        this.events.add(event);
    }
}