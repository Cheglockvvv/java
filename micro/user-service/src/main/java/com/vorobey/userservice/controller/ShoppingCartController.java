package com.vorobey.userservice.controller;

import com.vorobey.userservice.cart.CartItem;
import com.vorobey.userservice.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public void addToCart(@RequestParam Long userId, @RequestBody CartItem cartItem) {
        shoppingCartService.addToCart(userId, cartItem);
    }
}
