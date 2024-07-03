package com.vorobey.userservice.client;

import com.vorobey.productservice.entity.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class InventoryServiceClient {

    private final RestTemplate restTemplate;

    //TODO change port and url into a property file or git
    private final String gatewayUrl = "http://localhost:8765/inventory-service";

    public Map<Long, Integer> getProductStock() {
        String url = gatewayUrl + "/inventory/stocks";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Map<Long, Integer>>() {}
        ).getBody();
    }
}
