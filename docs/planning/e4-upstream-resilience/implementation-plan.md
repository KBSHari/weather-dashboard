# E4 Upstream Resilience (RestTemplate)

## Objective
Add upstream resilience for Open‑Meteo calls behind existing endpoints:
- `GET /api/geocode?query=...`
- `GET /api/weather?lat=...&lon=...`

## Agreed defaults
- `openmeteo.timeout.read=5s`
- `openmeteo.retry.max-attempts=3` (total attempts incl. first)
- `openmeteo.retry.backoff=200ms`
- Timeouts are **retryable** (bounded by max-attempts).

## Proposed config keys
- `openmeteo.geocoding-base-url=https://geocoding-api.open-meteo.com`
- `openmeteo.weather-base-url=https://api.open-meteo.com`
- `openmeteo.timeout.read=5s`
- `openmeteo.retry.max-attempts=3`
- `openmeteo.retry.backoff=200ms`

## Open decisions (TBD)
1. Error JSON schema (fields + example).
2. Retryable statuses: 5xx only? include 429?
3. Retry exhaustion status: 502 vs 503.

## Bound (for docs)
Worst-case ~ `(3 * 5s) + (2 * 200ms)` ≈ **15.4s** plus overhead.
