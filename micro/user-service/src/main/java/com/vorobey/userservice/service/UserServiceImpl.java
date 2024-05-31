package com.vorobey.userservice.service;

import com.vorobey.productservice.entity.ProductEntity;
import com.vorobey.userservice.client.InventoryServiceClient;
import com.vorobey.userservice.client.ProductServiceClient;
import com.vorobey.userservice.util.ProductWithStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private InventoryServiceClient inventoryServiceClient;

    @Autowired
    private ProductServiceClient productServiceClient;

    @Override
    public List<ProductWithStock> getAllProductsWithStock() {
        List<ProductEntity> availableProducts = productServiceClient.getAllProducts();
        Map<Long, Integer> productStock = inventoryServiceClient.getProductStock();

        return availableProducts.stream()
                .map(product -> new ProductWithStock(product, productStock.getOrDefault(product.getId(), 0)))
                .collect(Collectors.toList());
    }
}
