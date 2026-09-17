# 🌐 SpatialHub — Broadband Infrastructure GIS & Operations Portal

[![Live Demo](https://img.shields.io/badge/Demo-Live_on_Render-2ea44f?style=for-the-badge&logo=render)](https://spatialhub-gis.onrender.com)
[![Java](https://img.shields.io/badge/Java-17-orange.svg?style=for-the-badge&logo=openjdk)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen.svg?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-Cloud_Managed-blue.svg?style=for-the-badge&logo=mysql)](https://aiven.io/)
[![Leaflet](https://img.shields.io/badge/Leaflet-1.9.4-green.svg?style=for-the-badge&logo=leaflet)](https://leafletjs.com/)
[![Docker](https://img.shields.io/badge/Docker-Multi--Stage-blue.svg?style=for-the-badge&logo=docker)](https://www.docker.com/)

**SpatialHub** is a full-stack Geographic Information System (GIS) and Network Operations Center (NOC) application tailored for telecom and broadband infrastructure management. It allows operators to survey physical assets, calculate road-snapped fiber conduit paths, and manage field incident work orders in real time.

---

## 🚀 Live Demo & Credentials

* **Live URL:** [spatialhub-gis.onrender.com](https://spatialhub-gis.onrender.com)
* *(Note: Hosted on Render Free Tier. Please allow 30–45 seconds for cold start if the instance is sleeping).*

| Role | Username | Password | Permissions |
| :--- | :--- | :--- | :--- |
| **NOC Dispatcher** | `dispatcher` | `admin123` | Full network monitoring, ticket assignment, asset telemetry |
| **Field Technician** | `technician` | `tech123` | Field survey entry, issue reporting, ticket resolution |

---

## 🛠️ Key Architectural Highlights

* **Geospatial Fiber Routing Engine:** Interfaces with the Open Source Routing Machine (OSRM) API to dynamically snap optical fiber paths to real-world street networks rather than simple point-to-point lines.
* **Role-Based Access & Security:** Stateless session tokens with BCrypt password hashing and custom route guards preventing unauthorized navigation or infinite reload states.
* **High-Efficiency GeoJSON Telemetry:** Serves optimized FeatureCollections and spatial aggregations from a managed cloud MySQL database via Spring Data JPA.
* **Production Docker Containerization:** Multi-stage build leveraging `maven:3.9.6` for clean artifact compilation and a minimal `alpine-jre` runtime layer for fast startup and low memory footprint.

---

## 🏗️ Tech Stack

* **Backend:** Java 17, Spring Boot (Web, Data JPA, Security Crypto)
* **Frontend:** Vanilla JavaScript, Leaflet.js, OpenStreetMap Tiles, Responsive CSS3
* **Database:** MySQL (Hosted on Aiven Cloud)
* **Routing Engine:** Project OSRM API
* **Deployment & CI/CD:** Docker (Multi-stage), Render Cloud Platform, GitHub Actions-ready

---

## ⚡ Local Setup

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/Shiy4dh/spatialhub-gis.git](https://github.com/Shiy4dh/spatialhub-gis.git)
   cd spatialhub-gis
