# Test plan — E4 (MockWebServer)

## Strategy
Integration tests using Spring Boot + `MockWebServer`.
Override base URLs to point to mock server; no external network.

## Suggested test overrides
- `openmeteo.timeout.read=200ms`
- `openmeteo.retry.max-attempts=3`
- `openmeteo.retry.backoff=10ms`
- `openmeteo.*-base-url=http://localhost:{port}`

## Cases
1. **Success (200)**: upstream 200 -> API 200; request count = 1.
2. **Timeout retryable -> eventual 504**: delay > timeout for all attempts -> API 504; request count = 3.
3. **Retry then success**: 502, 502, 200 -> API 200; request count = 3.
4. **Retry exhausted (5xx)**: 503, 503, 503 -> API 5xx (TBD 502/503); request count = 3.
5. **Non-retryable 4xx**: 400/404 -> API mapped 4xx; request count = 1.

## Error schema assertions
Until schema is finalized, assert minimally:
- JSON content type
- a stable user-safe `message` field (or placeholder agreed by team)
