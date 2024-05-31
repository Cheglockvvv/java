package com.vorobey.userservice.service;

import com.vorobey.userservice.util.ProductWithStock;

import java.util.List;

public interface UserService {
    List<ProductWithStock> getAllProductsWithStock();
}
