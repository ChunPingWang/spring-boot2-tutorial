package com.mes.sleuth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TracingService {

    private static final Logger log = LoggerFactory.getLogger(TracingService.class);

    public Map<String, Object> processWithTracing(String requestId) {
        String traceId = UUID.randomUUID().toString();
        String spanId = UUID.randomUUID().toString();
        
        log.info("[SLEUTH DEMO] Starting process with traceId={}, spanId={}, requestId={}", 
                traceId, spanId, requestId);
        
        Map<String, Object> result = new HashMap<>();
        result.put("traceId", traceId);
        result.put("spanId", spanId);
        result.put("requestId", requestId);
        result.put("status", "PROCESSED");
        
        log.info("[SLEUTH DEMO] Completed process with traceId={}", traceId);
        
        return result;
    }

    public Map<String, Object> logWithCorrelation(String operation) {
        String correlationId = UUID.randomUUID().toString();
        
        log.info("[CORRELATION] Starting operation={}, correlationId={}", operation, correlationId);
        
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        Map<String, Object> result = new HashMap<>();
        result.put("operation", operation);
        result.put("correlationId", correlationId);
        result.put("completed", true);
        
        log.info("[CORRELATION] Completed operation={}, correlationId={}", operation, correlationId);
        
        return result;
    }
}
