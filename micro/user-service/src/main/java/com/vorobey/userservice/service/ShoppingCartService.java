package com.vorobey.userservice.service;

import com.vorobey.userservice.entity.cart.Cart;
import com.vorobey.userservice.entity.cart.CartItem;

public interface ShoppingCartService {
    void addToCart(Long userId, CartItem cartItem);
    Cart getCart(Long userId);
    void removeFromCart(Long userId, Long productId);
    void clearCart(Long userId);
    String checkAndMakeOrder(Long userId);
}
