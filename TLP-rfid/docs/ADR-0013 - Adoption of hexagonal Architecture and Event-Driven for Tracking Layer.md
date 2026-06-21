# ADR-0013: Adoption of Hexagonal Architecture and Event-Driven for Tracking Layer

## Status
Accepted (2026-06-21)

## Context
The TLP needed to evolve from an RFID + basic prediction system into a **Logistics Intelligence Platform**, integrating multiple data sources (GPS, RFID, and future IoT sensors). Direct integration of GPS into the domain or existing services would create strong coupling and make it difficult to add new providers (TomTom, Samsara, Wialon, MQTT, etc.).

## Decision
- Adopt **Hexagonal Architecture** with clear separation between:
  - **Domain** (TrackingEvent, ShipmentTrackingSession, Value Objects)
  - **Application** (TrackingService, TrackingEnrichmentService)
  - **Infrastructure/Adapters** (GpsIntegrationAdapter)
  - **Interfaces** (REST + WebSocket)
- Use **Event-Driven Architecture** with Spring `ApplicationEvent`:
  - `GpsReceivedEvent` → `TrackingUpdatedEvent` → `PredictionService`

## Alternatives Considered
- Direct synchronous service calls
- Early adoption of Kafka or RabbitMQ
- Keeping everything inside the existing `rfid/` or `integration/` packages

## Positive Consequences
- High decoupling: domain knows nothing about GPS
- Easy extension for new sensors (temperature, cameras, telemetry)
- Better testability and maintainability
- Natural preparation for future scaling and microservices

## Negative Consequences
- Moderate increase in initial complexity
- Need for database migrations for new entities

## References
- Implementation steps 2 to 6 of the GPS + Tracking layer