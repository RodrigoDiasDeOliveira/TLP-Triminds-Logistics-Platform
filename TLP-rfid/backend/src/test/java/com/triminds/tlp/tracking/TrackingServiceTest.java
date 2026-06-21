package com.triminds.tlp.tracking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.triminds.tlp.common.domain.GpsCoordinate;
import com.triminds.tlp.common.domain.TrackingEventType;

@SpringBootTest
class TrackingServiceTest {

    @Autowired
    private TrackingService trackingService;

    @Test
    void shouldProcessGpsAndRfidEvents() {
        TrackingEvent gpsEvent = TrackingEvent.builder()
                .companyId(1L)
                .shipmentId("SHIP-001")
                .vehicleId("VEH-001")
                .eventType(TrackingEventType.GPS_UPDATE)
                .coordinate(GpsCoordinate.of(-23.5505, -46.6333))
                .build();

        trackingService.processTrackingEvent(gpsEvent);

        // Asserts...
    }
}
