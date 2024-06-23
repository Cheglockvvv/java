package com.vorobey.userservice.cart;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart implements Serializable {
    private Long userId;
    private Map<Long, CartItem> items = new HashMap<>();

    public Cart(Long userId) {
        this.userId = userId;
    }

    public void addItem(CartItem item) {
        CartItem currentCartItem = items.get(item.getProductId());
        if (currentCartItem == null) {
            items.put(item.getProductId(), item);
        } else {
            currentCartItem.setQuantity(currentCartItem.getQuantity() + item.getQuantity());
            items.put(item.getProductId(), currentCartItem);
        }
    }

    public void removeItem(CartItem item) {
        items.remove(item.getProductId());
    }
}
