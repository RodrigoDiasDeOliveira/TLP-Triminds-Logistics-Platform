package com.triminds.tlp.integration.mapper;

import com.triminds.tlp.integration.dto.RfidEventDTO;
import com.triminds.tlp.rfid.model.EventType;
import com.triminds.tlp.rfid.model.RfidEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class IntegrationMapper {

    public RfidEvent toEntity(RfidEventDTO dto) {
        RfidEvent entity = new RfidEvent();

        entity.setTagId(dto.getTagId());
        entity.setLocation(dto.getLocation());
        entity.setProductCode(dto.getItem());
        entity.setCompanyId(dto.getCompanyId());
        entity.setEventType(parseEventType(dto.getAction()));
        entity.setTimestamp(dto.getTimestamp() != null ? dto.getTimestamp() : LocalDateTime.now());

        return entity;
    }

    public RfidEventDTO toDTO(RfidEvent entity) {
        RfidEventDTO dto = new RfidEventDTO();

        dto.setTagId(entity.getTagId());
        dto.setLocation(entity.getLocation());
        dto.setAction(entity.getEventType() != null ? entity.getEventType().name() : null);
        dto.setItem(entity.getProductCode());
        dto.setCompanyId(entity.getCompanyId());
        dto.setTimestamp(entity.getTimestamp());

        return dto;
    }

    public List<RfidEvent> toEntityList(List<RfidEventDTO> dtos) {
        return dtos.stream().map(this::toEntity).toList();
    }

    public List<RfidEventDTO> toDTOList(List<RfidEvent> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    private EventType parseEventType(String action) {
        if (action == null || action.isBlank()) {
            return EventType.SCAN;
        }

        return switch (action.trim().toUpperCase()) {
            case "ENTRY", "IN" -> EventType.ENTRY;
            case "EXIT", "OUT" -> EventType.EXIT;
            case "TRANSFER", "MOVE" -> EventType.TRANSFER;
            case "INVENTORY" -> EventType.INVENTORY;
            default -> EventType.SCAN;
        };
    }
}
