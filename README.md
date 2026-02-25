
## 🏢 Central Server Monitoring & Alerting System

A centralized monitoring and alerting system that collects metrics from distributed server agents, analyzes resource usage, and triggers multi-stage alerts.

---

## 🚀 Overview

This system:

* Receives metrics from multiple servers
* Stores data in PostgreSQL
* Analyzes utilization levels
* Detects abnormal resource usage
* Triggers alert escalation

---

## 🏗️ Architecture

```
[ Server Agents ]
       │
       ▼
[ REST API Endpoint ]
       ▼
[ Database ]
       ▼
[ Analysis Engine ]
       ▼
[ Alert System ]
```

---

## 📊 What It Monitors

### 🔹 Server-Level Monitoring

* CPU Utilization
* RAM Utilization
* Memory Usage
* Server Status

### 🔹 Service-Level Monitoring

* Service Status
* Health
* Port-based services
* Name-based services

---

## 🚨 Alerting & Escalation Logic

### 🟡 Stage 1 — Email Alert

If:

```
Ex:
CPU Utilization > 85%
```

➡ Send Email to responsible employee.

---

### 🔴 Stage 2 — WhatsApp Escalation

If the issue:

* Persists for **2 days**
* Still above threshold

➡ Send WhatsApp notification to designated employee.

---

## 🧠 Alert Workflow

```
High CPU Detected
        ↓
Send Email Alert
        ↓
Wait N Hours
        ↓
If Not Resolved → Send WhatsApp Escalation
```

---

## 🔎 Search & Filtering Features

The system allows:

* Search by Server Name
* Search by Service Name
* Filter by Port Range
* View historical utilization
* Track unresolved alerts

---

## 🗄️ Database Schema

Stores:

* Servers
* Services
* Metrics History
* Alert Logs
* Escalation Status

---

## 📧 Notification Channels

### Email Integration

* SMTP Configuration
* HTML formatted alerts

### WhatsApp Integration

* WhatsApp API integration
* Escalation messages for unresolved critical issues

---

## ⚙️ Configuration Example

```properties
# Alert Threshold
alert.cpu.threshold=85

# Escalation Period (hours)
alert.escalation.hours=48

# Email Config
mail.smtp.host=smtp.example.com
mail.smtp.port=587

# WhatsApp API Config
whatsapp.api.url=https://api.whatsapp-service.com/send
```

---

## 📈 Example Alert Message

### Email:

```
Subject: High CPU Usage Alert

Server: Server-01
CPU Usage: 92%
Status: CRITICAL
```

### WhatsApp:

```
CRITICAL ALERT
Server-01 CPU usage above M% for N hours.
Immediate action required.
```

---

## 🛠️ Technologies Used

* Quarkus
* REST APIs
* PostgreSQL
* Email (SMTP)
* WhatsApp API Integration

---

## ▶️ Running the Project

```bash
./mvnw quarkus:dev
```

Build:

```bash
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

---

# 🎯 Full System Summary

| Component      | Responsibility                           |
| -------------- | ---------------------------------------- |
| Server Agent   | Collects metrics & sends every 5 minutes |
| Central System | Stores, analyzes & alerts                |
| Email          | First alert stage                        |
| WhatsApp       | Escalation stage after N hours          |



# server-servies

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/server-servies-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and
  Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on
  it.
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus
  REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
