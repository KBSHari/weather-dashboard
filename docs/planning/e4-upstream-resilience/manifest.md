# Manifest — E4 Upstream Resilience

## Impact
- Backend: `WeatherController`, new service/client, RestTemplate config, retry logic, exception mapping.
- Tests: MockWebServer integration tests.
- Docs: README/config docs.

## Risks
- Retries can increase load/latency (bounded to ~15.4s worst-case).
- Timeout too low/high; default 5s.
- Error JSON schema TBD may require follow-up.

## Rollout
- Ship with defaults (5s, 3 attempts, 200ms).
- Validate in staging using MockWebServer-like scenarios.

## Rollback
- Revert PR or set `openmeteo.retry.max-attempts=1` to disable retries.
