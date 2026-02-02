# AI-Powered Learning Platform

**Full-stack LMS with context-aware AI tutoring and persistent RAG-lite capabilities.**

## 🏗 System Architecture

* **Backend:** Java 17 / Spring Boot (REST API)
* **Frontend:** React (SPA)
* **Database:** PostgreSQL (Relational persistence)
* **Security:** Spring Security + BCrypt (Stateful/Session-based)
* **AI Integration:** Section-contextual prompt engineering via external LLM API

---

## 🛠 Technical Implementation

### 🔐 Security & Auth

* **Authentication:** Custom `UserDetailsService` implementation with BCrypt password encoding.
* **Authorization:** Role-based access control (RBAC) protecting course management and AI endpoints.
* **Global Exception Handling:** `@ControllerAdvice` mapping domain exceptions to standard HTTP status codes.

### 🧠 Context-Aware AI Logic

* **The "Section-Aware" Engine:** The backend fetches the current `Section` content (Markdown) and injects it into the LLM system prompt, ensuring responses are grounded in the specific lecture data.
* **Persistence:** Chat sessions are mapped to `(User, Section)` pairs in PostgreSQL, allowing for historical retrieval and multi-session management.

### 💾 Data Modeling

* **Hierarchical Content:** `Course 1 -> N Sections`.
* **Chat Schema:** `ChatSession` holds metadata; `ChatMessage` holds the content and role (User/Assistant) with timestamps for chronological rendering.
* **Migrations:** Transitioned from H2 (volatile) to PostgreSQL (persistent) for production readiness.

---

## 🔌 API Overview

| Endpoint | Method | Description |
| --- | --- | --- |
| `/api/auth/**` | POST | Login/Registration (Public) |
| `/api/courses` | GET | List all available modules |
| `/api/sections/{id}` | GET | Retrieve Markdown content for a section |
| `/api/ai/chat` | POST | Send message + Contextual metadata |
| `/api/ai/history/{sectionId}` | GET | Fetch persistent chat history |

---

## 🚀 Deployment & CI/CD

* **Pipeline:** GitHub Actions automates `mvn clean verify` and frontend builds on every push.
* **Environment:** Production-ready configuration using Spring Profiles (`application-prod.yml`).
* **Logging:** SLF4J/Logback integration with severity filtering.

---

## 🛠 Setup

```bash
# Backend
./mvnw spring-boot:run -Dspring.profiles.active=dev

# Frontend
npm install && npm start

```

---

## 📈 Future Scope

* **Vector Search:** Moving from simple context injection to RAG using a Vector DB.
* **Analytics:** Tracking student engagement via AI query patterns.
