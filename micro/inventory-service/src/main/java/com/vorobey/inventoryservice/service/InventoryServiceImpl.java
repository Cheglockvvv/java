package com.vorobey.inventoryservice.service;

import com.vorobey.inventoryservice.entity.InventoryEntity;
import com.vorobey.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    private InventoryRepository inventoryRepository;


    @Override
    public List<InventoryEntity> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    @Override
    public Map<Long, Integer> getProductStock(Long productId) {
        return Map.of();
    }

    @Override
    public InventoryEntity updateInventoryItem(Long productId, Integer newQuantity) {
        Optional<InventoryEntity> inventoryEntityOpt = inventoryRepository.findById(productId);
        if (inventoryEntityOpt.isPresent()) {
            InventoryEntity inventoryEntity = inventoryEntityOpt.get();
            inventoryEntity.setQuantity(newQuantity);
            return inventoryRepository.save(inventoryEntity);
        } else {
            throw new RuntimeException("Item with product id: " + productId + " not found");
        }
    }

    @Override
    public InventoryEntity addInventoryItem(InventoryEntity inventoryEntity) {
        return inventoryRepository.save(inventoryEntity);
    }

    @Override
    public void deleteInventoryItem(Long id) {
        inventoryRepository.deleteById(id);
    }
}
