package com.mes.sleuth.adapter.in.web;

import com.mes.sleuth.service.TracingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/tracing")
public class TracingController {

    private final TracingService tracingService;

    public TracingController(TracingService tracingService) {
        this.tracingService = tracingService;
    }

    @GetMapping("/process/{requestId}")
    public ResponseEntity<Map<String, Object>> process(@PathVariable String requestId) {
        return ResponseEntity.ok(tracingService.processWithTracing(requestId));
    }

    @PostMapping("/log")
    public ResponseEntity<Map<String, Object>> log(@RequestBody Map<String, String> request) {
        String operation = request.getOrDefault("operation", "default-operation");
        return ResponseEntity.ok(tracingService.logWithCorrelation(operation));
    }
}
