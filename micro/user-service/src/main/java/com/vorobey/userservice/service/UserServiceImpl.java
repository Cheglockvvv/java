package com.vorobey.userservice.service;

import com.vorobey.userservice.cart.Cart;
import com.vorobey.userservice.cart.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vorobey.userservice.repository.CartRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addToCart(Long userId, Long productId, Integer quantity) {
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        Cart cart = optionalCart.orElseGet(Cart::new);
        cart.setUserId(userId);

        Optional<CartItem> optionalCartItem = cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
            cart.getItems().add(cartItem);
        }

        return cartRepository.save(cart);
    }

    //    @Autowired
//    private InventoryServiceClient inventoryServiceClient;

//    @Autowired
//    private ProductServiceClient productServiceClient;
//
//    @Override
//    public List<ProductWithStock> getAllProductsWithStock() {
//        List<ProductEntity> availableProducts = productServiceClient.getAllProducts();
//        Map<Long, Integer> productStock = inventoryServiceClient.getProductStock();
//
//        return availableProducts.stream()
//                .map(product -> new ProductWithStock(product, productStock.getOrDefault(product.getId(), 0)))
//                .collect(Collectors.toList());
//    }
}
