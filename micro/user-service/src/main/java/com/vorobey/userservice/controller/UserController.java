package com.vorobey.userservice.controller;

import com.vorobey.userservice.cart.Cart;
import com.vorobey.userservice.cart.CartItem;
import com.vorobey.userservice.service.ShoppingCartService;
import com.vorobey.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserController {

    @GetMapping("/test")
    public String instruments() {
        return "instruments";
    }

}
