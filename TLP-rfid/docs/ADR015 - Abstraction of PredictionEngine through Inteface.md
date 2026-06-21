# ADR-003: Abstraction of PredictionEngine through Interface

## Status
Accepted (2026-06-21)

## Context
The current prediction engine (DL4J + Weka) is only an initial stage. In the future, TLP should support models in Python, Azure ML, Vertex AI, or external services.

## Decision
Create `PredictionEngine` interface in the `prediction` package, with concrete implementations (`WekaPredictionEngine`, `ExternalAiPredictionEngine`, etc.).

## Alternatives Considered
- Keeping the current engine tightly coupled in `PredictionService`
- Using Strategy Pattern instead of interface

## Positive Consequences
- Easy technology swap without impacting the rest of the system
- Preparation for external AI service integration via WebClient
- Better separation of concerns

## References
- `PredictionEngine.java`
- `WekaPredictionEngine.java`
- `TrackingContext.java`