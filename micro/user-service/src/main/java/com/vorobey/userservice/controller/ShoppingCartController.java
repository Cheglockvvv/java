package com.vorobey.userservice.controller;

import com.vorobey.userservice.cart.Cart;
import com.vorobey.userservice.cart.CartItem;
import com.vorobey.userservice.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public void addToCart(@RequestParam Long userId, @RequestBody CartItem cartItem) {
        shoppingCartService.addToCart(userId, cartItem);
    }

    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestParam Long userId) {
        Optional<Cart> cartOptional = shoppingCartService.getCart(userId);
        return cartOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/remove")
    public void removeFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        shoppingCartService.removeFromCart(userId, productId);
    }

    @PostMapping("/clear")
    public void clearCart(@RequestParam Long userId) {
        shoppingCartService.clearCart(userId);
    }
}
