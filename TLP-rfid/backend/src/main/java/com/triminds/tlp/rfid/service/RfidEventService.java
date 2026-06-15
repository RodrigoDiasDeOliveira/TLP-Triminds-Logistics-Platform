package com.triminds.tlp.rfid.service;

import com.triminds.tlp.rfid.dto.RfidEventDTO;
import com.triminds.tlp.rfid.model.EventType;
import com.triminds.tlp.rfid.model.RfidEvent;
import com.triminds.tlp.rfid.repository.RfidEventRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RfidEventService {

    private final RfidEventRepository repository;

    public RfidEventService(RfidEventRepository repository) {
        this.repository = repository;
    }

    public RfidEvent registerEvent(String tagId, String location, String type) {
        EventType eventType = parseEventType(type);
        return repository.save(new RfidEvent(tagId, location, eventType));
    }

    public List<RfidEvent> getHistory(String tagId) {
        return repository.findByTagIdOrderByTimestampAsc(tagId);
    }

    public void ingestEvents(List<RfidEventDTO> events) {
        List<RfidEvent> entities = events.stream()
                .map(this::toEntity)
                .toList();

        repository.saveAll(entities);
    }

    public List<RfidEvent> getRealTimeEvents(String companyId, int limit) {
        int safeLimit = Math.max(1, Math.min(limit, 500));

        return repository.findByCompanyIdOrderByTimestampDesc(
                companyId,
                PageRequest.of(0, safeLimit)
        );
    }

    private RfidEvent toEntity(RfidEventDTO dto) {
        RfidEvent event = new RfidEvent();

        event.setTagId(dto.getTagId());
        event.setReaderId(dto.getReaderId());
        event.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now());
        event.setEventType(dto.getEventType() != null ? dto.getEventType() : EventType.SCAN);
        event.setLocation(dto.getLocation());
        event.setProductCode(dto.getProductCode());
        event.setCompanyId(dto.getCompanyId());

        return event;
    }

    private EventType parseEventType(String type) {
        if (type == null || type.isBlank()) {
            return EventType.SCAN;
        }

        return switch (type.trim().toUpperCase()) {
            case "ENTRY", "IN" -> EventType.ENTRY;
            case "EXIT", "OUT" -> EventType.EXIT;
            case "TRANSFER", "MOVE" -> EventType.TRANSFER;
            case "INVENTORY" -> EventType.INVENTORY;
            default -> EventType.SCAN;
        };
    }
}
