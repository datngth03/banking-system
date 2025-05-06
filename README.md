# 🏦 Online Banking System - Backend

This is a full-featured backend system for an Online Banking Application, built with Java and Spring Boot. It provides secure account management, transaction processing, loan management, and more.

---

## 📋 Table of Contents

- [🏦 Online Banking System - Backend](#-online-banking-system---backend)
  - [📋 Table of Contents](#-table-of-contents)
  - [🚀 Features](#-features)
    - [1. 🛡️ Authentication \& Session Management](#1-️-authentication--session-management)
    - [2. 👤 Customer Management](#2--customer-management)
    - [3. 🏦 Bank Accounts](#3--bank-accounts)
    - [4. 💸 Transactions](#4--transactions)
    - [5. 💳 Card Management](#5--card-management)
    - [6. 🧾 Loan Services](#6--loan-services)
    - [7. 👥 Beneficiaries](#7--beneficiaries)
    - [8. 🔔 Notifications](#8--notifications)
    - [9. 🕵️ Audit \& Monitoring](#9-️-audit--monitoring)
  - [🗃️ Entity-Relationship Diagram (ERD)](#️-entity-relationship-diagram-erd)
    - [Key Entities:](#key-entities)
  - [⚙️ Technology Stack](#️-technology-stack)
  - [🛠️ Getting Started](#️-getting-started)

---

## 🚀 Features

This Online Banking System provides core functionalities of digital banking, including:

### 1. 🛡️ Authentication & Session Management
- User Registration and Login
- Secure password hashing
- JWT-based authentication (Access & Refresh tokens)
- Redis session/token storage
- Email verification and OTP (optional)

### 2. 👤 Customer Management
- Create, view, update, deactivate customer profiles
- Role-based access control (User, Admin)

### 3. 🏦 Bank Accounts
- Create checking/savings accounts
- Deposit / Withdraw money
- Lock/Unlock accounts

### 4. 💸 Transactions
- Transfer money between accounts
- Transaction history with filters
- Unique transaction ID tracking
- Audit logs for all operations

### 5. 💳 Card Management
- Issue credit/debit cards
- Link cards to accounts
- Expiry/CVV management
- Lock/lost/stolen card status handling

### 6. 🧾 Loan Services
- Apply for personal or home loans
- Calculate interest and repayment schedule
- Track active, overdue, and paid loans

### 7. 👥 Beneficiaries
- Add/remove trusted transfer recipients
- Auto-fill details during transfers

### 8. 🔔 Notifications
- Real-time alerts on transactions
- Email/mobile notifications

### 9. 🕵️ Audit & Monitoring
- Full audit logs of all user actions
- Track IP address, device info, timestamps
- Useful for fraud detection and compliance


---

## 🗃️ Entity-Relationship Diagram (ERD)

Below is the complete ERD for the Online Banking System:

[👉 View Interactive ERD on dbdiagram.io](https://dbdiagram.io/d/Bank_System-680920301ca52373f514d855)  

### Key Entities:

- **Customer**: Stores personal information and login credentials
- **Account**: Links to customers; stores balance and account type
- **Card**: Debit/Credit cards associated with accounts
- **Transaction**: Stores deposit, withdrawal, and transfer records
- **Loan**: Tracks loan details like type, amount, and interest
- **Beneficiary**: Recipients that a customer can transfer money to
- **LoginSession**: Tracks login/logout activity
- **AuditLog**: Security and access logs
- **Notification**: Alerts and system messages for customers

---

## ⚙️ Technology Stack

- **Language**: Java 21  
- **Framework (Backend)**: Spring Boot  
- **ORM**: Hibernate / JPA  
- **Database**: PostgreSQL *(or MySQL)*  
- **Authentication & Authorization**: Spring Security with JWT  
- **Session Management**: Redis *(optional - for session storage if not using JWT)*  
- **Build Tool**: Maven  
- **Logging**: SLF4J + Logback  
- **Unit Testing**: JUnit + Mockito  
- **API Documentation**: Swagger / SpringDoc OpenAPI  
<!-- - **Database Migration**: Flyway or Liquibase   -->
- **Version Control**: Git + GitHub  
- **Dev Tools**: Spring Boot DevTools  
- **ERD Tool**: [dbdiagram.io](https://dbdiagram.io)  
- **IDE**: VS Code

---
```markdown
## 🛡️ Brute-Force Protection with Redis

This system implements a Redis-based **rate limiting** mechanism using **Bucket4j** to protect against brute-force login attacks. Here’s how it works:

1. **Rate Limiting**: Every login attempt is tracked via a **token bucket** stored in Redis. 
2. **Redis Configuration**: A unique `clientId` (could be user ID or IP) is used as the key in Redis.
3. **Login Attempts**: Users can only attempt to login a limited number of times within a fixed period (e.g., 10 attempts within 1 minute).
4. **Exceeding Attempts**: If the rate limit is exceeded, further login attempts are blocked with a `429 Too Many Requests` response.
5. **Refill Mechanism**: The token bucket is refilled at a fixed rate (e.g., 10 tokens every minute).


## 🛠️ Getting Started

<!-- 1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/online-banking-system.git
   cd online-banking-system -->
