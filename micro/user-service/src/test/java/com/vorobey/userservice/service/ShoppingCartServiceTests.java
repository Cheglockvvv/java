package com.vorobey.userservice.service;

import com.vorobey.userservice.entity.cart.CartItem;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ShoppingCartServiceTests {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Test
    public void get() {
        shoppingCartService.addToCart(1L, new CartItem(1L, 1));
        shoppingCartService.addToCart(2L, new CartItem(1L, 1));

        getAndPrint(shoppingCartService.getCart(1L).getUserId());
        getAndPrint(shoppingCartService.getCart(2L).getUserId());
        getAndPrint(shoppingCartService.getCart(1L).getUserId());
        getAndPrint(shoppingCartService.getCart(2L).getUserId());
    }

    private void getAndPrint(Long id) {
        //log.info("");
    }
}
