# 🚀 Intelligent Distributed Server Monitoring System

> Enterprise-grade distributed infrastructure monitoring and alerting platform designed for modern DevOps environments.

---

## 📌 Overview

This repository contains a **powerful distributed monitoring system** built using a **two-project architecture** designed for large-scale server environments.

The system continuously monitors:

* 🖥️ Server health
* ⚡ CPU utilization
* 🧠 RAM / Memory usage
* 🔌 Running services status
* 🌐 Service ports
* 📊 Infrastructure performance
* 🚨 Critical threshold violations

It supports both:

* 🍏 macOS
* 🐧 Linux Servers

The architecture is designed for scalability, reliability, automation, and real-time incident response.

---

# 🏗️ System Architecture

```text
                ┌─────────────────────┐
                │   Monitoring Agent  │
                │ (Installed on Node) │
                └─────────┬───────────┘
                          │
                          │ Sends Metrics
                          ▼
              ┌─────────────────────────┐
              │   Central Monitoring    │
              │        Server           │
              └─────────┬───────────────┘
                        │
        ┌───────────────┼────────────────┐
        ▼                               ▼
  Threshold Analysis             Alert Engine
        ▼                               ▼
 Email Notifications         WhatsApp Escalation
```

---

# 📦 Projects Structure

## 1️⃣ Monitoring Agent Project

This lightweight service is installed directly on every monitored server.

### 🔍 Responsibilities

* Collects:

  * CPU utilization
  * RAM usage
  * Memory statistics
  * Server status
  * Running services information
  * Port availability
* Sends collected data periodically to the central server
* Works on:

  * Linux
  * macOS
* Supports scheduled execution:

  * Every 5 minutes
  * Every hour
  * Custom intervals

---

### ⚙️ Features

✅ Cross-platform support
✅ Lightweight background service
✅ Automatic health reporting
✅ Service discovery by:

* Service name
* Partial service name
* Port range

✅ Dynamic port scanning

Example:

```text
Scan all services running on ports:
1000 → 10000
```

---

### 📡 Data Sent to Central Server

```json
{
  "serverName": "production-node-1",
  "cpuUsage": 82,
  "memoryUsage": 68,
  "services": [
    {
      "name": "nginx",
      "status": "RUNNING",
      "port": 8080
    }
  ]
}
```

---

# 2️⃣ Central Monitoring Server

The core intelligence layer of the platform.

This project receives, analyzes, and processes monitoring data from all connected servers.

---

## 🧠 Responsibilities

### 📊 Metrics Analysis

Analyzes:

* CPU utilization
* RAM consumption
* Service health
* Infrastructure stability
* Port availability
* Resource anomalies

---

### 🚨 Threshold Engine

Example thresholds:

| Metric       | Threshold | Status      |
| ------------ | --------- | ----------- |
| CPU Usage    | < 85%     | ✅ Healthy   |
| CPU Usage    | > 85%     | ⚠️ Warning  |
| Memory Usage | > 90%     | 🔥 Critical |

---

### 📧 Email Notification System

If abnormal behavior is detected:

* Detailed reports are generated
* DevOps engineers receive alerts instantly
* Includes:

  * Server name
  * Problem description
  * Resource statistics
  * Timestamp
  * Recommended actions

---

### 📱 WhatsApp Escalation System

If the issue remains unresolved for a configured duration (e.g. 2 days):

✅ The system automatically escalates the alert
✅ Sends WhatsApp notifications to responsible engineers
✅ Ensures incidents are never ignored

---

# 🔥 Advanced Features

## ✅ Intelligent Service Discovery

Search services by:

* Exact service name
* Partial service name
* Port range
* Running status

---

## ✅ Real-Time Monitoring

Near real-time infrastructure visibility.

---

## ✅ Automated Incident Escalation

Multi-level alerting strategy:

```text
Warning → Email → WhatsApp Escalation
```

---

## ✅ Distributed Architecture

Designed for:

* Cloud infrastructure
* Enterprise systems
* Large-scale server farms
* Microservices environments

---

## ✅ Extensible Design

Easy integration with:

* Docker
* Kubernetes
* Grafana
* Prometheus
* CI/CD Pipelines

---

# 🛠️ Technologies Used

## Monitoring Agent

* Java / Quarkus
* REST APIs
* Scheduled Jobs
* System Metrics APIs

---

## Central Server

* Java / Quarkus
* RESTful Services
* Background Processing
* Alert Engine
* Email Services
* WhatsApp Integration

---

# 📈 Monitoring Workflow

```text
1. Agent collects metrics
2. Metrics sent to central server
3. Central server analyzes data
4. Threshold validation executed
5. Incident generated if necessary
6. Email notification sent
7. Escalation timer starts
8. WhatsApp alert triggered if unresolved
```

---

# 🔐 Reliability & Scalability

The system was designed with:

* High scalability
* Fault tolerance
* Distributed monitoring
* Low resource consumption
* Modular architecture

in mind.

---

# 🎯 Use Cases

✅ Enterprise Infrastructure Monitoring
✅ DevOps Automation
✅ Data Centers
✅ Cloud Servers
✅ Production Systems
✅ CI/CD Environments
✅ High Availability Systems

---

# 🚀 Future Improvements

* Grafana Dashboard Integration
* AI-based anomaly detection
* Historical analytics
* Predictive infrastructure monitoring
* Auto-recovery actions
* Kubernetes-native deployment
* Slack / Microsoft Teams integration

---

# 📬 Alert Example

```text
[CRITICAL ALERT]

Server: production-node-3
CPU Usage: 94%
Memory Usage: 91%

Detected Issues:
- High CPU consumption
- Memory pressure
- nginx service instability

Action Required Immediately.
```

---

# 👨‍💻 Author

Developed with passion for building scalable infrastructure monitoring systems and improving DevOps automation.

---

# ⭐ Final Notes

This project demonstrates:

* Distributed systems engineering
* Infrastructure monitoring
* Backend architecture
* Automated alerting systems
* DevOps best practices
* Scalable software design

---

## 🌍 Enterprise Monitoring Made Smarter

> “Monitor everything. Miss nothing.”
