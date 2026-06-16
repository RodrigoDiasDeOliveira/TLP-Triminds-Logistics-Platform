# TLP - Triminds Logistics Platform

<img width="1536" height="1024" alt="Dashboard Screenshot" src="https://github.com/user-attachments/assets/8fd806af-447d-4e38-9692-8a83ff09ab3a" />

**Real-time Logistics Platform with RFID + Artificial Intelligence**

A **multi-tenant SaaS** solution for complete warehouse traceability using RFID technology, Machine Learning predictions, and advanced operational dashboards.

---

## 🎯 Current Features (June 2026)

### Implemented Functionalities

- **Real-time RFID Event Ingestion** (batch + simulator)
- **Automatic RFID Reader Simulator** (generates events every 2-3 seconds)
- **Live Operational Dashboard** with real-time KPIs
- **Machine Learning Predictions** (basic engine + business rules + DL4J ready)
- **Analytics & KPIs** (24h events, active tags, throughput, bottlenecks)
- **WebSocket** for live RFID event updates
- **Complete JWT Authentication** (login + security filter)
- **Basic Multi-tenant Architecture** (`companyId`)
- **Modern Frontend** with React + TypeScript + Ant Design + WebSocket

---

## 🚀 Technologies

### Backend
- **Java 17** + **Spring Boot 3.3**
- Spring Data JPA + H2 (development)
- Spring Security + JWT
- WebSocket (STOMP + SockJS)
- Deeplearning4j (DL4J)
- Lombok

### Frontend
- **React 18** + **TypeScript** + Vite
- Ant Design (UI Components)
- Axios + STOMP WebSocket
- Tailwind CSS

### DevOps & Infrastructure
- Maven
- Docker + Docker Compose
- GitHub Actions (planned)

---

## 📁 Project Structure
TLP-rfid/
└── backend/src/main/java/com/triminds/tlp/
├── security/           → JWT, Auth, SecurityConfig
├── websocket/          → WebSocketConfig and Handler
├── rfid/               → Events, Repository, Service, Controller + Simulator
├── prediction/         → PredictionService, MLEngine, DTOs
├── analytics/          → AnalyticsService and Controller
├── dto/                → Shared DTOs
└── TlpRfidApplication.java
text---

## 🏃 How to Run (Local)

### 1. Backend
```bash
cd TLP-rfid/backend
mvn clean install
mvn spring-boot:run
RFID simulator starts automatically.
2. Frontend
Bashcd TLP-rfid/frontend
npm install
npm run dev
Access: http://localhost:5173
Demo Credentials

Email: demo@triminds.com
Password: demo123


📡 Main Endpoints








































MethodEndpointDescriptionPOST/api/auth/loginJWT LoginPOST/api/rfid/events/ingestIngest RFID eventsGET/api/rfid/events/realtimeReal-time eventsGET/api/analytics/kpisOperational KPIsPOST/api/predictionMake predictionsGET/api/prediction/recentRecent predictions
WebSocket: /ws/rfid (STOMP)

✅ Current Status

Functional MVP: Yes
End-to-End Demo: Yes (RFID → Live Dashboard)
Machine Learning: Basic engine (ready for real models)
Production Ready: In progress


🚀 Roadmap (Next Steps)

 Complete Docker Compose setup
 Migrate to PostgreSQL + Oracle Cloud
 ERP Integration (Totvs / SAP)
 Train real ML models with DL4J
 Advanced RBAC + robust Multi-tenant
 Billing & SaaS Plans
 Swagger / OpenAPI Documentation
 Full CI/CD with GitHub Actions


Made with ❤️ by Triminds Initiative (2025–2026)