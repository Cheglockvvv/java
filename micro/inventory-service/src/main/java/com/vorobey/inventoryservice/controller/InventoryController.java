package com.vorobey.inventoryservice.controller;

import com.vorobey.inventoryservice.entity.InventoryEntity;
import com.vorobey.inventoryservice.service.InventoryService;
import com.vorobey.userservice.entity.cart.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/items")
    public List<InventoryEntity> getAllInventoryItems() {
        return inventoryService.getAllInventoryItems();
    }

    @GetMapping("/stocks")
    public Map<Long, Integer> getAllInventoryStocks() {
        return inventoryService.getProductStock();
    }

    @PostMapping("/add")
    public InventoryEntity addInventoryItem(@RequestBody InventoryEntity inventoryEntity) {
        return inventoryService.addInventoryItem(inventoryEntity);
    }

    @PutMapping("/items/{productId}")
    public InventoryEntity updateInventoryItem(@PathVariable Long productId,
                                               @RequestBody Integer quantity) {
        return inventoryService.updateInventoryItem(productId, quantity);
    }

    @DeleteMapping("/items/{id}")
    public void deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
    }

    @PostMapping("/available")
    public ResponseEntity<String> isAvailable(@RequestBody Cart cart) {
        if (inventoryService.isAvailable(cart)) {
            return ResponseEntity.ok("Available");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
