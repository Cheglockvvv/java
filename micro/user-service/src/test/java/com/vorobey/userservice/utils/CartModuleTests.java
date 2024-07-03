package com.vorobey.userservice.utils;

import com.vorobey.userservice.entity.cart.Cart;
import com.vorobey.userservice.entity.cart.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartModuleTests {

    private Cart cart;

    @BeforeEach
    public void setup() {
        cart = new Cart(56L);
    }

    @Test
    public void testAddItemToCart_WhenProduct_NotInCart() {
        // Given
        Long testProductId = 3L;
        Integer testQuantity = 5;
        CartItem cartItem = new CartItem(testProductId, testQuantity);

        // When
        cart.addItem(cartItem);

        // Then
        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().containsKey(testProductId));
        assertEquals(cartItem, cart.getItems().get(testProductId));
    }

    @Test
    public void testAddItemToCart_WhenProduct_InCart() {
        // Given
        Long testProductId = 3L;
        Integer testQuantity = 5;
        CartItem cartItem = new CartItem(testProductId, testQuantity);
        cart.addItem(new CartItem(testProductId, 3));

        // When
        cart.addItem(cartItem);

        // Then
        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().containsKey(testProductId));
        assertEquals(testQuantity + 3, cart.getItems().get(testProductId).getQuantity());
    }

    @Test
    public void testAddItemToCart_WhenOtherProduct_InCart() {
        // Given
        Long testProductId = 3L;
        Integer testQuantity = 5;
        Long testProductId2 = 19L;
        Integer testQuantity2 = 6;
        cart.addItem(new CartItem(testProductId, testQuantity));

        // When
        cart.addItem(new CartItem(testProductId2, testQuantity2));

        // Then
        assertEquals(2, cart.getItems().size());
        assertTrue(cart.getItems().containsKey(testProductId));
        assertTrue(cart.getItems().containsKey(testProductId2));
    }

    @Test
    public void testRemoveItemFromCart_WhenProduct_NotInCart() {
        // Given
        Long testProductId = 3L;
        Integer testQuantity = 5;
        CartItem cartItem = new CartItem(testProductId, testQuantity);
        cart.addItem(cartItem);

        // When
        cart.removeItem(5L);

        // Then
        assertEquals(1, cart.getItems().size());
        assertTrue(cart.getItems().containsKey(testProductId));
        assertFalse(cart.getItems().containsKey(5L));
        assertEquals(cartItem, cart.getItems().get(testProductId));
    }

    @Test
    public void testRemoveItemFromCart_WhenProduct_InCart() {
        // Given
        Long testProductId = 3L;
        Integer testQuantity = 5;
        CartItem cartItem = new CartItem(testProductId, testQuantity);
        cart.addItem(cartItem);

        // When
        cart.removeItem(3L);

        // Then
        assertEquals(0, cart.getItems().size());
        assertFalse(cart.getItems().containsKey(testProductId));
    }

    @Test
    public void testRemoveItemFromCart_WhenOtherProduct_InCart() {
        // Given
        Long testProductId = 3L;
        Integer testQuantity = 5;
        CartItem cartItem = new CartItem(testProductId, testQuantity);
        CartItem cartItem2 = new CartItem(testProductId + 1, testQuantity);
        cart.addItem(cartItem);
        cart.addItem(cartItem2);

        // When
        cart.removeItem(3L);

        // Then
        assertEquals(1, cart.getItems().size());
        assertFalse(cart.getItems().containsKey(testProductId));
        assertTrue(cart.getItems().containsKey(testProductId + 1));
        assertEquals(cartItem2, cart.getItems().get(testProductId + 1));
    }
}
