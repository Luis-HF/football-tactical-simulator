<h3 align="center">USE REGISTRATION (HAPPY PATH)</h3>
This flow describes the successful creation of a new account and its persistence in PostgreSQL.

```mermaid

sequenceDiagram
    autonumber
    actor User
    participant Front as Frontend (JS)
    participant Back as Backend (Java/Spring)
    participant DB as PostgreSQL

    User->>Front: Input data & click Register
    Front->>Back: fetch(POST /api/v1/accounts, JSON)
    Back->>DB: INSERT INTO Accounts (uuid, username, ...)
    DB-->>Back: ACK + generated UUID
    Back-->>Front: HTTP 201 Created (JSON Body)
    Front->>User: Notify Success and Redirect to Login Screen
```
<h3 align="center">ERROR BY LATENCY</h3>
```mermaid

sequenceDiagram
    autonumber
    actor User
    participant Front as Frontend (JS)
    participant Back as Backend (Java/Spring)

    User->>Front: Input registration data
    Front->>Back: fetch(POST /api/v1/accounts, JSON)

    Note over Front,Back: Request pending (Latency threshold exceeded)

    Front->>Front: Trigger timeout (AbortController)
    Front-->>User: Display "Server is taking too long to respond. Try again."
```
<h3 align="center">DATA CONFLICT</h3>
```mermaid

sequenceDiagram
    autonumber
    actor User
    participant Front as Frontend (JS)
    participant Back as Backend (Java/Spring)
    participant DB as PostgreSQL

    User->>Front: Input registration data
    Front->>Back: fetch(POST /api/v1/accounts, JSON)
    Back->>DB: INSERT INTO Accounts (uuid, username, ...)
    DB-->>Back: Error: Unique constraint violation
    Back-->>Front: HTTP 409 Conflict (Error JSON Message)
    Front-->>User: Display "This username/email is already registered."
```
<h3 align="center">USER AUTHENTICATE</h3>
```mermaid
sequenceDiagram
    autonumber
    actor User
    participant Front as Frontend (JS)
    participant Back as Backend (Java/Spring)
    participant DB as PostgreSQL

    User->>Front: Input credentials & click Login
    Front->>Back: fetch(POST /api/v1/auth/login, JSON)
    Back->>DB: SELECT FROM Accounts WHERE username = ?
    DB-->>Back: Account Data
    Back->>Back: Validate Credentials
    Back-->>Front: HTTP 200 OK
    Front->>User: Redirect to Game Dashboard
```
<h3 align="center">INVALID CREDENTIALS</h3>
```mermaid
sequenceDiagram
    autonumber
    actor User
    participant Front as Frontend (JS)
    participant Back as Backend (Java/Spring)
    participant DB as PostgreSQL

    User->>Front: Input wrong credentials
    Front->>Back: fetch(POST /api/v1/auth/login, JSON)
    Back->>DB: SELECT FROM Accounts...
    DB-->>Back: Account not found OR password mismatch
    Back-->>Front: HTTP 401 Unauthorized (Error Message)
    Front-->>User: Display "Invalid username or password."