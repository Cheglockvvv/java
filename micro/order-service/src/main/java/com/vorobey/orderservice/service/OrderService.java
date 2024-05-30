package com.vorobey.orderservice.service;

import com.vorobey.orderservice.entity.OrderEntity;
import com.vorobey.orderservice.entity.OrderStatus;
import com.vorobey.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity createOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    public OrderEntity getOrderById(Long id) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findById(id);
        if (orderEntityOptional.isPresent()) {
            return orderEntityOptional.get();
        } else {
            throw new RuntimeException("Order with id " + id + " not found");
        }
    }

    public OrderEntity updateOrderStatus(Long id, OrderStatus status) {
        OrderEntity orderEntityToUpdate = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with id " + id + " not found"));
        orderEntityToUpdate.setStatus(status);
        return orderRepository.save(orderEntityToUpdate);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
