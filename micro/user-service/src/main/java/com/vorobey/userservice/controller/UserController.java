package com.vorobey.userservice.controller;

import com.vorobey.userservice.cart.CartItem;
import com.vorobey.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String addToCart(@RequestBody CartItem cartItem) {
        return "empty";
    }

//    @GetMapping("/products")
//    public List<ProductWithStock> getAllProductWithStock() {
//        return userService.getAllProductsWithStock();
//    }
}
