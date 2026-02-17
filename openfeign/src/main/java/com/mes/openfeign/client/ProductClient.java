package com.mes.openfeign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "mes-api-service", url = "${feign.client.url:http://localhost:8080}")
public interface ProductClient {

    @GetMapping("/api/v1/products/{id}")
    Map<String, Object> getProduct(@PathVariable("id") String id);

    @GetMapping("/api/v1/products")
    Map<String, Object> listProducts();
}
