package com.triminds.tlp.websocket;

import com.triminds.tlp.common.event.TrackingUpdatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrackingWebSocketHandler {

    private final SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void broadcastTrackingUpdate(TrackingUpdatedEvent event) {
        String destination = "/topic/tracking/" + event.getShipmentId();
        messagingTemplate.convertAndSend(destination, event);
        log.info("Atualização enviada via WebSocket para shipment: {}", event.getShipmentId());
    }
}