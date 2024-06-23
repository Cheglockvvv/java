package com.vorobey.userservice.service;

import com.vorobey.userservice.cart.Cart;

import java.util.List;

public interface UserService {
    Cart addToCart(Long userId, Long productId, Integer quantity);

    //List<ProductWithStock> getAllProductsWithStock();
}
