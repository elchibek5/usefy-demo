<div align="center">
  <h1>🎓 AiDA Learn</h1>
  <h3>
    <span style="color: #FF4B4B;">The LMS that adapts to you.</span>
  </h3>
  <p>
    AiDA (Artificial Intelligence Digital Assistant) is an enterprise-grade Learning Management System being built to use <strong>Generative AI</strong> for real-time personalized tutoring.
  </p>

  <a href="https://github.com/elchibek5/AiDA-Learn">
    <img src="https://img.shields.io/badge/Status-Under_Development-yellow?style=for-the-badge" alt="Status" />
  </a>

  <br>
  <br>

  ![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk)
  ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2-green?logo=spring)
  ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Enterprise-blue?logo=postgresql)
  ![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?logo=docker)
  ![OpenAI](https://img.shields.io/badge/OpenAI-GPT--4-412991?logo=openai&logoColor=white)

</div>

---

## 🏗️ System Architecture

AiDA follows a **Domain-Driven Design (DDD)** approach with a clear separation of concerns.

```mermaid
graph TD
    User((User)) -->|HTTPS / JSON| API_Gateway["API Gateway / Controller"]
    
    subgraph "Core Backend (Spring Boot)"
        API_Gateway -->|Validate Token| Security["Security Config (Stateless)"]
        Security -->|Authorized| Service["Learning Service"]
        Service -->|Read/Write| Repository["JPA Repository"]
    end
    
    subgraph "Data Persistence"
        Repository <-->|JDBC| DB[("PostgreSQL")]
        DB <-->|Cache| Redis[("Redis Cache")]
    end
    
    subgraph "AI Integration"
        Service -->|Prompt Engineering| AI_Client["OpenAI Service"]
        AI_Client <-->|REST| GPT["OpenAI API"]
    end
````

-----

## 🛠️ Tech Stack

| Component | Technology | Description |
| :--- | :--- | :--- |
| **Language** | **Java 21** | Utilizing Records, Pattern Matching, and Virtual Threads. |
| **Framework** | Spring Boot 3.2 | Rapid backend development with Dependency Injection. |
| **Database** | PostgreSQL | Relational data integrity for user and course management. |
| **Security** | Spring Security 6 | OAuth2 Resource Server & JWT Authentication. |
| **AI Engine** | OpenAI GPT-4 | Dynamic quiz generation and context-aware tutoring. |
| **Container** | Docker | Consistent deployment across environments. |

-----

## 💡 Key Features (In Progress)

### 🔐 Secure Authentication

  * Implementation of **JWT (JSON Web Tokens)** for stateless session management.
  * Role-Based Access Control (RBAC) distinguishing between `STUDENT`, `INSTRUCTOR`, and `ADMIN`.

### 🤖 Generative AI Tutor

  * **Dynamic Quiz Generation:** System analyzes course text and generates 5 unique questions on-the-fly.
  * **Explanation Engine:** Uses RAG (Retrieval-Augmented Generation) to explain incorrect answers to students.

### ⚡ High Performance

  * Leveraging **Java 21 Virtual Threads** for high-throughput concurrency handling.
  * Optimized SQL queries using Hibernate/JPA projections.

-----

### Chat Feature
- Secure AI chat available only to authenticated users
- Protected by Spring Security
- Fully tested (backend + frontend)


## 🚀 Roadmap

  - [x] Initial Project Setup & Docker Configuration
  - [x] Database Schema Design (PostgreSQL)
  - [ ] Implement Spring Security Filter Chain
  - [ ] Connect OpenAI API Client
  - [ ] Build Frontend Dashboard (React)

-----

<div align="center">
  <p>
    Created by <a href="https://www.linkedin.com/in/elchibek-dastanov-55bb84308/">Elchibek Dastanov</a>
  </p>
</div>
