package com.vorobey.userservice.client;

import com.vorobey.productservice.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductServiceClient {
    @Autowired
    public RestTemplate restTemplate;

    public List<ProductEntity> getAllProducts() {
        return restTemplate.getForObject("http://product-service/products", List.class);
    }
}
