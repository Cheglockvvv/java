package com.vorobey.userservice.service.impl;

import com.vorobey.userservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
//    private CartRepository cartRepository;
//
//    @Override
//    public Cart addToCart(Long userId, Long productId, Integer quantity) {
//        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
//        Cart cart = optionalCart.orElseGet(Cart::new);
//        cart.setUserId(userId);
//
//        Optional<CartItem> optionalCartItem = cart.getItems().stream()
//                .filter(item -> item.getProductId().equals(productId))
//                .findFirst();
//
//        if (optionalCartItem.isPresent()) {
//            CartItem cartItem = optionalCartItem.get();
//            cartItem.setQuantity(cartItem.getQuantity() + quantity);
//        } else {
//            CartItem cartItem = new CartItem();
//            cartItem.setProductId(productId);
//            cartItem.setQuantity(quantity);
//            cart.getItems().add(cartItem);
//        }
//
//        return cartRepository.save(cart);
//    }

}
