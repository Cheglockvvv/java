package com.vorobey.inventoryservice.service;

import com.vorobey.inventoryservice.entity.InventoryEntity;
import com.vorobey.inventoryservice.exception.ProductNotFoundException;
import com.vorobey.inventoryservice.repository.InventoryRepository;
import com.vorobey.userservice.entity.cart.Cart;
import com.vorobey.userservice.entity.cart.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InventoryServiceImpl implements InventoryService{
    @Autowired
    private InventoryRepository inventoryRepository;


    @Override
    public List<InventoryEntity> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    @Override
    public Map<Long, Integer> getProductStock() {
        return inventoryRepository.findAll().stream()
                .collect(Collectors.toMap(InventoryEntity::getProductId, InventoryEntity::getQuantity));
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

    @Override
    public Boolean isAvailable(Cart cart) {
        List<CartItem> list = cart.getItems().keySet().stream()
                .map(id -> cart.getItems().get(id))
                .toList();
        for (CartItem cartItem : list) {
            Optional<InventoryEntity> inventoryEntityOpt = inventoryRepository.findByProductId(cartItem.getProductId());
            if (inventoryEntityOpt.isPresent()) {
                if (cartItem.getQuantity() < inventoryEntityOpt.get().getQuantity()) {
                    return false;
                }
            } else {
                throw new ProductNotFoundException("Product with id: " + cartItem.getProductId() + " not found");
            }
        }

        return true;
    }
}
