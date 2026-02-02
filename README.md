AI-Powered Learning Platform

A full-stack educational platform that combines structured course content with an AI tutoring assistant. The system supports user authentication, persistent chat history, course-based learning, and section-aware AI assistance, built with a production-ready backend and modern frontend architecture.

🚀 Features
🔐 Authentication & Security

User registration and login with BCrypt password hashing

Secure, role-aware API endpoints using Spring Security

Protected frontend routes for authenticated users

Centralized exception handling with consistent error responses

🧠 AI Tutoring System

Secure backend AI chat API with protected access

Section-aware AI assistant that answers questions based on course content

Each course section maintains its own persistent chat session

Ability to clear or restart AI conversations per section

💬 Chat & Persistence

Persistent chat sessions stored in PostgreSQL

Support for multiple chat sessions per user

Full conversation history retrieval

Clean domain modeling for chat sessions and messages

📚 Course & Content Management

Course and Section domain models with proper relationships

Markdown-based lecture content (no videos, text-first learning)

REST APIs to retrieve courses and their sections

Scalable data model designed for future expansion

🖥️ Frontend Experience

React-based frontend with protected routing

Course viewer with rendered Markdown lecture notes

Integrated AI assistant UI similar to IDE copilots

Dynamic chat UI with real-time message rendering

🧪 Testing & Quality

Unit tests for services, controllers, and repositories

Security-aware controller testing

CI pipeline running builds and tests automatically

Validation on both frontend and backend

📦 Infrastructure & Deployment

Migration from H2 to PostgreSQL

Environment-based configuration and secret management

First production deployment on cloud infrastructure

Backend logging with multiple severity levels (DEBUG → CRITICAL)

🛠 Tech Stack

Backend

Java 17

Spring Boot

Spring Security

Spring Data JPA

PostgreSQL

Hibernate

JUnit 5, Mockito

Frontend

React

JavaScript

Markdown rendering

Protected routing

DevOps & Tooling

Git & GitHub

GitHub Actions (CI)

Cloud deployment (Google Cloud / AWS)

Logging & monitoring

📐 Architecture Highlights

Layered architecture (Controller → Service → Repository)

DTO-based API design

Clean separation of concerns

Secure API key handling

Scalable relational data model

📌 Status

Core functionality complete.
The system is production-ready and designed for further expansion (analytics, course authoring, advanced AI features).
