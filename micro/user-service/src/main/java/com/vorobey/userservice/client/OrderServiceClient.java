package com.vorobey.userservice.client;

import com.vorobey.userservice.entity.cart.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class OrderServiceClient {
    private final RestTemplate restTemplate;

    private final String gatewayUrl = "http://localhost:8765/order-service";

    public ResponseEntity<String> makeOrder(Cart cart) {
        String url = gatewayUrl + "/api/v1/order";
        HttpEntity<Cart> httpEntity = new HttpEntity<>(cart);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
    }
}
