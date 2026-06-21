# ADR-0014: Creation of ShipmentTrackingSession Aggregate

## Status
Accepted (2026-06-21)

## Context
Tracking only the vehicle or only the RFID tag was insufficient. The core business question is: **"What is the complete status of a shipment?"**

## Decision
Create `ShipmentTrackingSession` as the **Aggregate Root** that:
- Links a `Vehicle` with one or more `Shipment`
- Contains the list of `TrackingEvent` (GPS + RFID)
- Maintains consolidated state (status, predictedEta, route)

## Alternatives Considered
- Direct extension of the `Shipment` entity
- Using only a generic events table

## Positive Consequences
- Model more faithful to logistics domain
- Native support for multiple deliveries per vehicle
- Central point for enrichment and prediction

## Negative Consequences
- Slight increase in relational model complexity

## References
- Domain: `ShipmentTrackingSession.java`
- Repository: `ShipmentTrackingSessionRepository.java`