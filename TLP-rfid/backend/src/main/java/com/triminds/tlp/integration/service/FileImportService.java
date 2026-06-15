package com.triminds.tlp.integration.service;

import com.triminds.tlp.rfid.model.EventType;
import com.triminds.tlp.rfid.model.RfidEvent;
import com.triminds.tlp.rfid.repository.RfidEventRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileImportService {

    private final RfidEventRepository rfidRepository;

    public FileImportService(RfidEventRepository rfidRepository) {
        this.rfidRepository = rfidRepository;
    }

    public int importFile(MultipartFile file, String type) throws Exception {
        return switch (type.toLowerCase()) {
            case "csv" -> importCsv(file);
            case "json" -> importJson(file);
            case "xml" -> importXml(file);
            default -> throw new IllegalArgumentException(
                    "Tipo de arquivo não suportado: " + type
            );
        };
    }

    private int importCsv(MultipartFile file) throws Exception {
        List<RfidEvent> events = new ArrayList<>();

        try (CSVParser parser = new CSVParser(
                new InputStreamReader(file.getInputStream()),
                CSVFormat.DEFAULT
                        .withFirstRecordAsHeader()
                        .withIgnoreHeaderCase()
                        .withTrim()
        )) {
            for (CSVRecord record : parser) {
                RfidEvent event = new RfidEvent();

                event.setTagId(record.get("tagId"));
                event.setLocation(record.get("location"));
                event.setTimestamp(LocalDateTime.now());

                if (record.isMapped("action")) {
                    event.setEventType(parseEventType(record.get("action")));
                } else {
                    event.setEventType(EventType.SCAN);
                }

                if (record.isMapped("item")) {
                    event.setProductCode(record.get("item"));
                }

                if (record.isMapped("companyId")) {
                    event.setCompanyId(record.get("companyId"));
                }

                events.add(event);
            }
        }

        rfidRepository.saveAll(events);

        return events.size();
    }

    private int importJson(MultipartFile file) {
        return 0;
    }

    private int importXml(MultipartFile file) {
        return 0;
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
