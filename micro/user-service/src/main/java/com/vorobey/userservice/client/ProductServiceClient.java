package com.vorobey.userservice.client;

import com.vorobey.productservice.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductServiceClient {
    @Autowired
    public RestTemplate restTemplate;

    private final String gatewayUrl = "http://localhost:8765/product-service";

    public List<ProductEntity> getAllProducts() {
        String url = gatewayUrl + "/products";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductEntity>>() {}
        ).getBody();
    }
}
