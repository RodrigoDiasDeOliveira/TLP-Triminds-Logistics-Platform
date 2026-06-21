package com.triminds.tlp.shipment.model;

import com.triminds.tlp.tracking.ShipmentTrackingSession;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long companyId;

    private String shipmentNumber;        // Número identificador da carga
    private String origin;
    private String destination;

    private String vehicleId;
    private String driverName;

    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;        // Enum existente ou novo

    private LocalDateTime departureTime;
    private LocalDateTime estimatedArrivalTime;
    private LocalDateTime actualArrivalTime;

    // === NOVOS CAMPOS PARA TRACKING INTELIGENTE ===
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "tracking_session_id")
    private ShipmentTrackingSession trackingSession;

    private LocalDateTime predictedEta;
    private String currentTrackingStatus;

    private String rfidTag;               // Tag principal associada

    // === MÉTODOS DE DOMÍNIO ===
    
    public void startTracking() {
        if (this.trackingSession == null) {
            this.trackingSession = ShipmentTrackingSession.builder()
                    .companyId(this.companyId)
                    .shipmentId(this.id.toString())
                    .vehicleId(this.vehicleId)
                    .startTime(LocalDateTime.now())
                    .currentStatus("IN_TRANSIT")
                    .build();
        }
        this.currentTrackingStatus = "IN_TRANSIT";
    }

    public void updateTrackingInfo(String status, LocalDateTime predictedEta) {
        this.currentTrackingStatus = status;
        this.predictedEta = predictedEta;
        
        if (this.trackingSession != null) {
            this.trackingSession.setCurrentStatus(status);
            this.trackingSession.setPredictedEta(predictedEta != null ? predictedEta.toString() : null);
        }
    }

    public boolean isDelayed() {
        return predictedEta != null && LocalDateTime.now().isAfter(predictedEta);
    }
}