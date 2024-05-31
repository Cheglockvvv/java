package com.vorobey.userservice.controller;

import com.vorobey.userservice.service.UserService;
import com.vorobey.userservice.util.ProductWithStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/products")
    public List<ProductWithStock> getAllProductWithStock() {
        return userService.getAllProductsWithStock();
    }
}
