package com.mes.openfeign.service;

import com.mes.openfeign.client.ProductClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    private final ProductClient productClient;

    public ProductService(ProductClient productClient) {
        this.productClient = productClient;
    }

    public Map<String, Object> getProduct(String id) {
        try {
            return productClient.getProduct(id);
        } catch (Exception e) {
            Map<String, Object> fallback = new HashMap<>();
            fallback.put("id", id);
            fallback.put("name", "Fallback Product");
            fallback.put("source", "feign-fallback");
            return fallback;
        }
    }

    public Map<String, Object> listProducts() {
        try {
            return productClient.listProducts();
        } catch (Exception e) {
            Map<String, Object> fallback = new HashMap<>();
            fallback.put("products", new Object[]{});
            fallback.put("source", "feign-fallback");
            return fallback;
        }
    }
}
