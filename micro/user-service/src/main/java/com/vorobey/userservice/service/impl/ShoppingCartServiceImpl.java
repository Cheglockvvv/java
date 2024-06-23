package com.vorobey.userservice.service.impl;

import com.vorobey.userservice.cart.Cart;
import com.vorobey.userservice.cart.CartItem;
import com.vorobey.userservice.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
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
        log.info("Item was added to cart successfully : {}", cartItem);
    }

    @Override
    public Optional<Cart> getCart(Long userId) {
        String cartKey = CART_PREFIX + userId.toString();
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartKey);
        return Optional.ofNullable(cart);
    }

    @Override
    public void removeFromCart(Long userId, Long productId) {
        String cartKey = CART_PREFIX + userId.toString();
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartKey);
        if (cart != null) {
            cart.removeItem(productId);
            redisTemplate.opsForValue().set(cartKey, cart, 1, TimeUnit.DAYS);
            log.info("Item was removed from cart successfully : {}", cartKey);
        } else {
            log.info("Item was not removed from cart : {} does not exist", cartKey);
        }
    }

    @Override
    public void clearCart(Long userId) {
        String cartKey = CART_PREFIX + userId.toString();
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartKey);
        if (cart != null) {
            redisTemplate.delete(cartKey);
            log.info("cart was cleared successfully : {}", cartKey);
        }
    }
}
