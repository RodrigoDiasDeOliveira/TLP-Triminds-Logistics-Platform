
<img width="1536" height="1024" alt="ChatGPT Image 2 de jun  de 2026, 11_48_33" src="https://github.com/user-attachments/assets/8fd806af-447d-4e38-9692-8a83ff09ab3a" />

TLP - Triminds Logistics Platform

**Real-time Logistics Platform with RFID + AI**

A **multi-tenant SaaS** solution for complete warehouse traceability using RFID technology, Machine Learning predictions, and operational dashboards.

---

## 🎯 Current Features (June 2026)

### Implemented Functionalities:

- **Real-time RFID Event Ingestion** (batch + simulator)
- **Automatic RFID Reader Simulator** (generates events every 2-3 seconds)
- **Live Operational Dashboard** with real-time KPIs
- **Machine Learning Predictions** (basic engine + rules + DL4J ready)
- **Analytics & KPIs** (24h events, active tags, throughput, bottlenecks)
- **WebSocket** for live RFID event updates
- **Complete JWT Authentication** (login + security filter)
- **Basic Multi-tenant Architecture** (`companyId`)
- **Modern Frontend** with React + TypeScript + Ant Design + WebSocket

---

## 🚀 Technologies

### Backend
- **Spring Boot 3.3** + Java 17
- Spring Data JPA + H2 (development)
- Spring Security + JWT
- WebSocket (STOMP + SockJS)
- DL4J (Deeplearning4j) ready for ML
- Lombok

### Frontend
- React 18 + TypeScript + Vite
- Ant Design (UI Components)
- Axios + WebSocket (STOMP)
- Tailwind CSS

### DevOps & Infra
- Maven
- Docker + Docker Compose
- GitHub Actions (planned)

---

## 📁 Project Structure
src/main/java/com/triminds/tlp/
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
mvn clean install
mvn spring-boot:run
The RFID simulator starts automatically.
2. Frontend
Bashcd frontend
npm install
npm run dev
Access: http://localhost:5173
Demo Credentials

Email: demo@triminds.com
Password: demo123


📡 Main Endpoints
Auth

POST /api/auth/login → JWT Login

RFID

POST /api/rfid/events/ingest
GET /api/rfid/events/realtime?companyId=empresa-demo

Analytics

GET /api/analytics/kpis?companyId=empresa-demo

Prediction

POST /api/prediction
GET /api/prediction/recent?companyId=empresa-demo

WebSocket

/ws/rfid (STOMP)


✅ Current Status

Functional MVP: Yes
End-to-End Demo: Yes (RFID → Live Dashboard)
ML: Basic engine (ready for real models)
Production Ready: In progress (H2 database, no billing yet, basic multi-tenant)


🚀 Next Steps (Roadmap)

Complete Docker Compose setup
Migrate to Oracle Cloud / PostgreSQL
ERP Integration (Totvs, SAP)
Train real ML models (DL4J)
Advanced RBAC + Multi-tenant
Billing & SaaS Plans
Swagger/OpenAPI Documentation


Triminds Initiative (2025–2026)
