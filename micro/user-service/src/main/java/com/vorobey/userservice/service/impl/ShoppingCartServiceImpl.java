package com.vorobey.userservice.service.impl;

import com.vorobey.userservice.cart.Cart;
import com.vorobey.userservice.cart.CartItem;
import com.vorobey.userservice.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final String CART_PREFIX = "cart:";
    private final RedisTemplate<String, Object> redisTemplate;


    @Override
    public void addToCart(Long userId, CartItem cartItem) {
        String cartKey = CART_PREFIX + userId.toString();
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartKey);
        if (cart == null) {
            cart = new Cart(userId);
        }
        cart.addItem(cartItem);
        redisTemplate.opsForValue().set(cartKey, cart, 1, TimeUnit.DAYS);
    }

    @Override
    public Optional<Cart> getCart(Long userId) {
        String cartKey = CART_PREFIX + userId.toString();
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartKey);
        return Optional.ofNullable(cart);
    }

    @Override
    public void removeFromCart(Long userId, CartItem cartItem) {

    }

    @Override
    public void clearCart(Long userId) {

    }
}
