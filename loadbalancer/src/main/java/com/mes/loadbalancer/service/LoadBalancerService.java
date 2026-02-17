package com.mes.loadbalancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoadBalancerService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    public Map<String, Object> callService(String serviceName) {
        ServiceInstance instance = loadBalancerClient.choose(serviceName);
        
        Map<String, Object> result = new HashMap<>();
        if (instance != null) {
            result.put("host", instance.getHost());
            result.put("port", instance.getPort());
            result.put("serviceId", instance.getServiceId());
            result.put("uri", instance.getUri());
        } else {
            result.put("message", "No instances available");
        }
        return result;
    }

    public Map<String, Object> callServiceWithRestTemplate(String serviceName) {
        try {
            String url = "http://" + serviceName + "/api/v1/health";
            String response = restTemplate.getForObject(url, String.class);
            
            Map<String, Object> result = new HashMap<>();
            result.put("response", response);
            result.put("using", "RestTemplate with @LoadBalanced");
            return result;
        } catch (Exception e) {
            Map<String, Object> result = new HashMap<>();
            result.put("error", e.getMessage());
            result.put("using", "RestTemplate with @LoadBalanced");
            return result;
        }
    }
}
