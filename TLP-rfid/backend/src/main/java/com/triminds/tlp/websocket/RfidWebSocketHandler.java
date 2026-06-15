package com.triminds.tlp.websocket;

import com.triminds.tlp.rfid.dto.RfidEventDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class RfidWebSocketHandler {

    @MessageMapping("/rfid-event")
    @SendTo("/topic/rfid-events")
    public RfidEventDTO handleRfidEvent(RfidEventDTO event) {
        System.out.println("WebSocket - Evento RFID recebido: " + event.getTagId());
        return event;
    }
    
    public void getTagId() {

    }
}