package com.triminds.tlp.prediction.engine;

import com.triminds.tlp.prediction.model.PredictionResult;
import com.triminds.tlp.rfid.model.RfidTag;
import com.triminds.tlp.rfid.repository.RfidEventRepository;
import com.triminds.tlp.shipment.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoricalEngine {

    private final RfidEventRepository rfidRepo;
    private final ShipmentRepository shipmentRepo;

    public HistoricalEngine(
            RfidEventRepository rfidRepo,
            ShipmentRepository shipmentRepo
    ) {
        this.rfidRepo = rfidRepo;
        this.shipmentRepo = shipmentRepo;
    }

    public PredictionResult predict(RfidTag tag) {
        return new PredictionResult(
                tag.getTagId(),
                tag.getLocation() != null ? tag.getLocation() : "analysis-required",
                "HISTORICAL",
                0.65
        );
    }

    public long countEventsLast24h() {
        return rfidRepo.count();
    }

    public long countShipments() {
        return shipmentRepo.count();
    }
}
