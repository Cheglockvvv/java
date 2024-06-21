package com.vorobey.userservice.controller;

import com.vorobey.userservice.cart.Cart;
import com.vorobey.userservice.cart.CartItem;
import com.vorobey.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String instruments() {
        return "instruments";
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestParam Long userId, @RequestBody CartItem cartItem) {
        Cart cart = userService.addToCart(userId, cartItem.getProductId(), cartItem.getQuantity());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

//    @GetMapping("/products")
//    public List<ProductWithStock> getAllProductWithStock() {
//        return userService.getAllProductsWithStock();
//    }
}
