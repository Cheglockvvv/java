package com.vorobey.orderservice.controller;

import com.vorobey.orderservice.entity.OrderEntity;
import com.vorobey.orderservice.entity.OrderStatus;
import com.vorobey.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderEntity> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public ResponseEntity<OrderEntity> createOrder(@RequestBody OrderEntity orderEntity) {
        OrderEntity entity = orderService.createOrder(orderEntity);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/{id}")
    public OrderEntity getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}")
    public OrderEntity updateOrderStatus(@PathVariable Long id, @RequestBody OrderStatus orderStatus) {
        return orderService.updateOrderStatus(id, orderStatus);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }

}
