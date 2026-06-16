# ADR-0012: High-Level Package and Class Overview (Code Map)

## Status
Accepted

## Date
2026-06-16

## Context
The TLP project follows a domain-driven structure, but there is no central document that clearly presents the main packages, key classes, and their relationships. This makes onboarding new developers and long-term maintenance more difficult as the codebase grows.

## Decision

We will maintain this document as a **living high-level code map** of the backend architecture. It will be updated periodically as the project evolves.

### 1. Main Packages (Backend)

| Package              | Main Responsibility                          | Key Classes |
|----------------------|----------------------------------------------|-------------|
| `security`           | Authentication, Authorization and Multi-tenancy | `JwtService`, `SecurityConfig`, `TenantFilter`, `AuthController`, `UserDetailsServiceImpl` |
| `websocket`          | Real-time communication via WebSocket        | `WebSocketConfig`, `RfidWebSocketHandler`, `RfidEventMessage` |
| `rfid`               | RFID event ingestion, persistence and simulation | `RfidEvent`, `RfidEventRepository`, `RfidEventService`, `RfidEventController`, `RfidSimulator` |
| `analytics`          | Calculation of KPIs and operational metrics  | `AnalyticsService`, `AnalyticsController`, `KpiCalculator` |
| `prediction`         | Prediction engine (Rule Engine + ML preparation) | `PredictionService`, `RuleEngine`, `MLEngine`, `FeatureBuilder`, `HistoricalEngine`, `PredictionController` |
| `dto`                | Data Transfer Objects (API requests/responses) | Various DTOs per domain |
| `entity` / `model`   | JPA entities and domain models               | `RfidEvent`, `PredictionResult`, `AnalyticsSummary`, etc. |

### 2. Main Flows and Relationships

**Core Flow (RFID → Dashboard):**
1. `RfidSimulator` generates events
2. `RfidEventController` receives events (HTTP)
3. `RfidEventService` persists and publishes via WebSocket
4. `RfidWebSocketHandler` broadcasts to connected clients
5. `AnalyticsService` updates real-time KPIs
6. `PredictionService` triggers rules or ML predictions

**Prediction + Analytics Integration:**
- `AnalyticsService` provides historical and aggregated data
- `FeatureBuilder` extracts features from RFID events
- `RuleEngine` / `MLEngine` consume features and produce `PredictionResult`
- Results are sent back to the dashboard via WebSocket

**Multi-tenancy:**
- `TenantFilter` intercepts all requests
- `companyId` is propagated across all services and repositories

### 3. Layer Responsibilities
- **Controller** → Handles HTTP requests and responses
- **Service** → Business logic and orchestration
- **Repository** → Data access (JPA)
- **Engine** → Specialized components (Rule Engine, ML Engine, etc.)

## Consequences

### Positive
- Faster onboarding for new developers
- Clear visibility of responsibilities and dependencies
- Easier identification of technical debt and refactoring opportunities
- Serves as reference for future architecture decisions

### Negative
- Requires periodic maintenance to stay accurate
- Risk of becoming outdated if not updated

## References
- ADR-0002: Domain-Driven Structure
- ADR-0004: Prediction Engine Architecture
- ADR-0005: Analytics Layer Design
- ADR-0006: Multi-tenant Architecture
- ADR-0007: Security Architecture (JWT)

![alt text](mermaid-diagram.svg)