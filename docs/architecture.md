# Architecture Design - Tactical Soccer Simulator

This document outlines the high-level architecture, design patterns, and technical decisions governing the Tactical Soccer Simulator ecosystem.

---

## 1. Architectural Pattern: Layered Architecture
The project utilizes a **Layered Architecture** (Multitier) to promote separation of concerns, maintainability, and testability. By decoupling the business logic from infrastructure, we ensure that changes in the database or UI do not directly impact the core simulation engine.



### Backend Layers (Spring Boot)
* **Controller Layer (API):** Acts as the entry point for HTTP requests. Responsible for request mapping, basic DTO validation, and returning appropriate HTTP Status Codes.
* **Service Layer (Business Logic):** The "brain" of the application. It orchestrates business rules, tactical calculations, and coordinates data flow between repositories and the in-memory state.
* **Repository Layer (Persistence):** An abstraction layer for data access using Spring Data JPA. It handles communication with the PostgreSQL database.
* **Domain Model:** Represents the core entities (Account, Lobby). These are the fundamental objects that the system operates upon.

---

## 2. Technology Stack & Component Roles

| Technology | Role |
| :--- | :--- |
| **Java 21** | Modern, high-performance language utilizing the latest JVM features. |
| **Spring Boot 3.x** | Framework for rapid development, dependency injection, and RESTful services. |
| **PostgreSQL** | Primary relational database for persistent, long-term data. |
| **ConcurrentHashMap** | In-memory thread-safe storage for low-latency lobby management. |
| **Docker & Compose** | Infrastructure orchestration for environment consistency. |
| **Vanilla JS (ES6+)** | Frontend logic, rendering, and state synchronization without heavy frameworks. |

---

## 3. Storage Strategy: Persistent vs. Volatile
A key architectural decision was the implementation of a **dual-storage strategy** to balance data integrity with real-time performance.

### Persistent Storage (PostgreSQL)
Used for data that must survive system restarts and require ACID compliance:
- **User Accounts:** Credentials and profiles.
- **Match History:** Records of completed tactical duels.
- **Statistics:** Long-term player performance data.

### Volatile Storage (In-Memory Map)
Used for transient, high-frequency data:
- **Active Lobbies:** Since lobbies are short-lived and require frequent updates (joining, leaving, status changes), they are stored in a `ConcurrentHashMap`.
- **Rationale:** This avoids the overhead of constant Database I/O for data that is inherently temporary. Thread-safety is guaranteed by the `Concurrent` implementation to handle simultaneous requests from multiple managers.



---

## 4. Communication & Integration
The system follows a **Decoupled Client-Server** model:

* **REST API:** The primary communication channel for administrative tasks (Registration, Authentication, Lobby Settings).
* **Data Format:** JSON is used exclusively for request and response payloads to ensure interoperability.
* **State Management:** The backend remains the "Source of Truth". The frontend captures user tactical decisions and sends them to the server for validation and processing.

---

## 5. Design Principles (SOLID & Clean Code)
To ensure the codebase remains robust as the Match Engine grows, we adhere to:
* **Dependency Injection (DI):** Leveraging Spring’s IoC container to keep components loosely coupled.
* **Single Responsibility Principle (SRP):** Each class has one specific reason to change (e.g., separating authentication logic from game simulation).
* **DTO Pattern:** Utilizing Data Transfer Objects to prevent exposing internal database schemas directly to the client.

---

## 6. Infrastructure & Deployment
The entire ecosystem is containerized using **Docker**. This ensures that the "It works on my machine" problem is eliminated by packaging the JRE, PostgreSQL, and Nginx/Frontend into a single, predictable environment.