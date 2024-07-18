package com.vorobey.inventoryservice.service;

import com.vorobey.inventoryservice.entity.InventoryEntity;
import com.vorobey.userservice.entity.cart.Cart;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    List<InventoryEntity> getAllInventoryItems();
    Map<Long, Integer> getProductStock();
    InventoryEntity updateInventoryItem(Long productId, Integer newQuantity);
    InventoryEntity addInventoryItem(InventoryEntity inventoryEntity);
    void deleteInventoryItem(Long id);
    Boolean isAvailable(Cart cart);
}
