package com.example.weatherdash.controller;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final RestTemplate rest;

    public WeatherController(RestTemplateBuilder builder) {
        this.rest = builder.build();
    }

    // Proxy geocoding (search by place name)
    @GetMapping("/geocode")
    public ResponseEntity<String> geocode(@RequestParam String query) {
        String q = UriUtils.encode(query, StandardCharsets.UTF_8);
        String url = "https://geocoding-api.open-meteo.com/v1/search?name=" + q;
        return rest.getForEntity(url, String.class);
    }

    // Proxy current weather (by latitude/longitude)
    @GetMapping("/weather")
    public ResponseEntity<String> weather(@RequestParam double lat, @RequestParam double lon) {
        String url = String.format(
                "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current_weather=true&timezone=auto",
                lat, lon);
        return rest.getForEntity(url, String.class);
    }
}
