package com.vorobey.userservice.client;

import com.vorobey.productservice.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class InventoryServiceClient {
    @Autowired
    private RestTemplate restTemplate;

    public Map<Long, Integer> getProductStock() {
        return restTemplate.getForObject("http://inventory-service/inventory/stock", Map.class);
    }
}
