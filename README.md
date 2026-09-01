# Weather Dashboard (Java / Maven demo)

Small demo Spring Boot application that proxies Open‑Meteo APIs and serves a tiny frontend.

Features
- Search locations by name using Open‑Meteo Geocoding API
- Fetch current weather for a selected location from Open‑Meteo
- No API key required (Open‑Meteo is free)

Prerequisites
- Java 17+
- Maven 3.6+

Run
1. Build and run:
   mvn spring-boot:run

2. Open the frontend:
   http://localhost:8080/

Usage
- Enter a city or place name, click Search.
- Click a returned location to view current weather.

Notes
- This demo proxies API calls through the backend to avoid CORS and to keep the frontend simple.
- For production use consider:
  - Caching responses
  - Rate limiting and error handling
  - Better UI/UX and weather code mapping
  - HTTPS and security hardening

APIs used
- Geocoding: https://geocoding-api.open-meteo.com/v1/search?name=...
- Weather: https://api.open-meteo.com/v1/forecast?latitude=...&longitude=...&current_weather=true
