package com.vorobey.userservice.service.impl;

import com.vorobey.userservice.client.InventoryServiceClient;
import com.vorobey.userservice.client.OrderServiceClient;
import com.vorobey.userservice.entity.cart.Cart;
import com.vorobey.userservice.entity.cart.CartItem;
import com.vorobey.userservice.exception.CartIsEmptyException;
import com.vorobey.userservice.exception.CartNotFoundException;
import com.vorobey.userservice.exception.ProductIsNotAvailableException;
import com.vorobey.userservice.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final String CART_PREFIX = "cart:";
    private final InventoryServiceClient inventoryServiceClient;
    private final OrderServiceClient orderServiceClient;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void addToCart(Long userId, CartItem cartItem) {
        log.info("adding item to cart");
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
    @Cacheable(value = "carts")
    public Cart getCart(Long userId) {
        log.info("getting cart by user id:");
        String cartKey = CART_PREFIX + userId.toString();
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartKey);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        log.info("Cart found: {}", cart);
        return cart;
    }

    @Override
    public void removeFromCart(Long userId, Long productId) {
        log.info("removing item from cart");
        String cartKey = CART_PREFIX + userId.toString();
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartKey);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        cart.removeItem(productId);
        redisTemplate.opsForValue().set(cartKey, cart, 1, TimeUnit.DAYS);
        log.info("Item was removed from cart successfully : {}", cartKey);
    }

    @Override
    public void clearCart(Long userId) {
        log.info("clearing cart by user id:");
        String cartKey = CART_PREFIX + userId.toString();
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartKey);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        redisTemplate.delete(cartKey);
        log.info("cart was cleared successfully : {}", cartKey);
    }

    @Override
    public String checkAndMakeOrder(Long userId) {
        log.info("begin making order");
        String cartKey = CART_PREFIX + userId.toString();
        Cart cart = (Cart) redisTemplate.opsForValue().get(cartKey);
        if (cart == null) {
            throw new CartNotFoundException();
        } else if (cart.getItems().isEmpty()) {
            throw new CartIsEmptyException();
        }

        ResponseEntity<String> response = inventoryServiceClient.isAvailable(cart);
        if (response.getStatusCode().is4xxClientError()) {
            throw new ProductIsNotAvailableException(response.getBody());
        }

        ResponseEntity<String> responseOrder = orderServiceClient.makeOrder(cart);

        return responseOrder.toString();
    }
}
