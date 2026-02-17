package com.mes.loadbalancer.adapter.in.web;

import com.mes.loadbalancer.service.LoadBalancerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/loadbalancer")
public class LoadBalancerController {

    private final LoadBalancerService loadBalancerService;

    public LoadBalancerController(LoadBalancerService loadBalancerService) {
        this.loadBalancerService = loadBalancerService;
    }

    @GetMapping("/choose/{serviceName}")
    public ResponseEntity<Map<String, Object>> chooseInstance(@PathVariable String serviceName) {
        return ResponseEntity.ok(loadBalancerService.callService(serviceName));
    }

    @GetMapping("/call/{serviceName}")
    public ResponseEntity<Map<String, Object>> callService(@PathVariable String serviceName) {
        return ResponseEntity.ok(loadBalancerService.callServiceWithRestTemplate(serviceName));
    }
}
