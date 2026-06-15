package com.triminds.tlp.rfid.controller;

import com.triminds.tlp.rfid.dto.RfidEventDTO;
import com.triminds.tlp.rfid.model.RfidEvent;
import com.triminds.tlp.rfid.service.RfidEventService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rfid")
public class RfidEventController {

    private final RfidEventService rfidEventService;

    public RfidEventController(RfidEventService rfidEventService) {
        this.rfidEventService = rfidEventService;
    }

    @PostMapping("/events/ingest")
    public ResponseEntity<Void> ingestEvents(@Valid @RequestBody List<RfidEventDTO> events) {
        rfidEventService.ingestEvents(events);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/events/realtime")
    public ResponseEntity<List<RfidEvent>> getRealTimeEvents(
            @RequestParam String companyId,
            @RequestParam(defaultValue = "50") int limit
    ) {
        return ResponseEntity.ok(
                rfidEventService.getRealTimeEvents(companyId, limit)
        );
    }

    @GetMapping("/events/history/{tagId}")
    public ResponseEntity<List<RfidEvent>> getHistory(@PathVariable String tagId) {
        return ResponseEntity.ok(rfidEventService.getHistory(tagId));
    }

    @GetMapping("/kpis")
    public ResponseEntity<Map<String, Object>> getKpis(@RequestParam String companyId) {
        return ResponseEntity.ok(Map.of(
                "status", "ok",
                "companyId", companyId
        ));
    }
}
