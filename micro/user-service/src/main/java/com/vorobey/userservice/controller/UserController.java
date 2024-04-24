package com.vorobey.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class UserController {
    @GetMapping("/test")
    public String instruments() {
        return "instruments";
    }

}
