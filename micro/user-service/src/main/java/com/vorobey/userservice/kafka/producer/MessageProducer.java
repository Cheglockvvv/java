package com.vorobey.userservice.kafka.producer;

import com.vorobey.userservice.cart.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProducer {

    private static final String INVENTORY_TOPIC = "inventory_topic";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendInventoryRequest(Long userId, Cart cart) {
        kafkaTemplate.send(INVENTORY_TOPIC, userId.toString(), cart);
    }
}
