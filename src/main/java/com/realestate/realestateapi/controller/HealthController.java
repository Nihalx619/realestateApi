package com.realestate.realestateapi.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A tiny endpoint that proves the app is up and serving requests.
 * Once running, hit it at: GET http://localhost:8080/api/health
 */
@RestController
@RequestMapping("/api")
public class HealthController {

	@GetMapping("/health")
	public Map<String, String> health() {
		return Map.of(
				"status", "UP",
				"service", "realestate-api"
		);
	}

}
