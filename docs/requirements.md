# Software Requirements Specification (SRS)

This document details the features and constraints of the Tactical Soccer Simulator, organized by delivery cycles (Sprints).

##  Overview
The objective is to provide a tactical simulation environment where business rules and network logic override mechanical execution, ensuring a pure strategy experience.

---

##  Functional Requirements (User Stories)

### Sprint 1: Foundation & Authentication
* **US01 - Account Registration:** Allows new managers to create a unique profile with email, username, and password for data persistence.
* **US02 - Authentication (Login):** Validates user credentials against the PostgreSQL database to grant access to restricted areas of the simulator.

### Sprint 2: Lobbies & Infrastructure (In Progress)
* **US03 - Lobby Creation:** An authenticated user can generate a unique 6-character code to open a private waiting room.
* **US04 - Join Lobby:** An opponent can enter the generated code to connect to the Host's game session.
* **TS-01 - Containerization (Docker):** Implementation of an isolated environment to ensure parity between development and production.

---

##  Non-Functional Requirements (Quality & Constraints)

### 1. Security & Privacy
- **NFR-01:** (Technical Debt) Implementation of BCrypt Hashing for all stored passwords (Priority: Sprint 2).
- **NFR-02:** Secure communication via REST API following HTTP verb standards and status codes.

### 2. Performance & Scalability
- **NFR-03:** In-memory lobby management (`ConcurrentHashMap`) to ensure real-time response (< 100ms).
- **NFR-04:** Relational data persistence for user profiles using PostgreSQL.

### 3. Reliability & Infrastructure
- **NFR-05:** Service orchestration via Docker Compose, isolating Database, Backend, and Frontend.
- **NFR-06:** Frontend and Backend input validation to prevent inconsistent system states.

---

##  Future Requirements Roadmap
- [ ] **Sprint 3:** Match Engine (Physics and probability simulation).