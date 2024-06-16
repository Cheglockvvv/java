package com.vorobey.userservice.controller;

import com.vorobey.userservice.service.UserService;
import com.vorobey.userservice.util.ProductWithStock;
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
    public String addToCart(@RequestBody ProductWithStock productWithStock) {
        return "empty";
    }

//    @GetMapping("/products")
//    public List<ProductWithStock> getAllProductWithStock() {
//        return userService.getAllProductsWithStock();
//    }
}
