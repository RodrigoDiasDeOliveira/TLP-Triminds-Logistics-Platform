package com.triminds.tlp.tracking;

import com.triminds.tlp.common.domain.GpsCoordinate;
import com.triminds.tlp.common.domain.TrackingEventType;
import com.triminds.tlp.common.domain.VehicleSpeed;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tracking_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackingEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long companyId;

    private String shipmentId;           // Referência à shipment
    private String vehicleId;            // Identificador do veículo
    private String rfidTag;              // Opcional (integração com RFID)

    @Enumerated(EnumType.STRING)
    private TrackingEventType eventType;

    @Embedded
    private GpsCoordinate coordinate;

    @Embedded
    private VehicleSpeed speed;

    private String locationDescription;  // Ex: "Próximo a São Paulo - SP"
    private LocalDateTime eventTime;

    private String additionalData;       // JSON para dados extras
}