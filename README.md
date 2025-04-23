# 💰 Online Banking System - Backend

This is a full-featured backend system for an Online Banking Application, built with Java and Spring Boot. It provides secure account management, transaction processing, loan management, and more.

---

## 📋 Table of Contents

- [💰 Online Banking System - Backend](#-online-banking-system---backend)
  - [📋 Table of Contents](#-table-of-contents)
  - [✨ Features](#-features)
  - [🗃️ Entity-Relationship Diagram (ERD)](#️-entity-relationship-diagram-erd)
    - [Key Entities:](#key-entities)
  - [⚙️ Technology Stack](#️-technology-stack)
  - [🛠️ Getting Started](#️-getting-started)

---

## ✨ Features

- User Registration & Authentication (JWT)
- Bank Account Management (Savings, Current)
- Transaction Management (Deposit, Withdraw, Transfer)
- Card Management (Credit, Debit)
- Loan Requests & Repayments
- Beneficiary Management
- Transaction History
- Audit Logging
- Admin Dashboard Support
- Email Notifications

---

## 🗃️ Entity-Relationship Diagram (ERD)

Below is the complete ERD for the Online Banking System:

![ERD](./A_comprehensive_Entity-Relationship_Diagram_(ERD)_.png)

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
- **Framework**: Spring Boot
- **Security**: Spring Security with JWT
- **Database**: PostgreSQL (or MySQL)
- **ORM**: Hibernate / JPA
- **Build Tool**: Maven
- **Version Control**: Git + GitHub

---

## 🛠️ Getting Started

<!-- 1. **Clone the Repository**
   ```bash
   git clone https://github.com/your-username/online-banking-system.git
   cd online-banking-system -->
