package com.vorobey.userservice.service;

import com.vorobey.userservice.cart.Cart;
import com.vorobey.userservice.cart.CartItem;

public interface ShoppingCartService {
    void addToCart(Long userId, CartItem cartItem);
    Cart getCart(Long userId);
    void removeFromCart(Long userId, CartItem cartItem);
    void clearCart(Long userId);
}
