package com.triminds.tlp.rfid.dto;

import com.triminds.tlp.rfid.entity.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RfidEventDTO {
    private String tagId;
    private String readerId;
    private LocalDateTime timestamp;
    private EventType eventType;
    private String location;
    private String productCode;
    private String companyId;
    private Map<String, Object> metadata;

    

    
}
