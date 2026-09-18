# Task breakdown — E4 (RestTemplate)

1) **Extract Open‑Meteo calls to a service**
- Objective: single place for outbound behavior.
- Changes: create `OpenMeteoService` (or `OpenMeteoClient`); controller delegates.
- DoD: controller contains no direct Open‑Meteo URL construction/calls.

2) **RestTemplate read timeout (default 5s)**
- Objective: predictable upstream failure.
- Changes: configure request factory timeouts via `RestTemplateBuilder`.
- DoD: timeout configurable; enforced in integration tests.

3) **Bounded retries (3 attempts, 200ms backoff)**
- Objective: recover from transient failures.
- Changes: Spring Retry (`@Retryable/@Recover`) or manual retry loop.
- DoD: retries on I/O + configured 5xx; no retries on 4xx.

4) **Error handling (schema TBD)**
- Objective: safe/consistent responses.
- Changes: `@RestControllerAdvice` mapping:
  - timeout -> 504
  - retry exhausted -> 5xx (TBD)
  - upstream 4xx -> mapped (no retry)
- DoD: no stack traces/internal details in responses.

5) **MockWebServer integration tests**
- Objective: deterministic tests without live Open‑Meteo.
- DoD: covers success, timeout, retry success, retry exhausted, non-retryable 4xx.

6) **Docs**
- Objective: document config + bounds.
- DoD: README updated with defaults and worst-case duration guidance.
