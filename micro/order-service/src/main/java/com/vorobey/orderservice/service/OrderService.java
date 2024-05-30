package com.vorobey.orderservice.service;

import com.vorobey.orderservice.entity.OrderEntity;
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

    public OrderEntity updateOrder(Long id, OrderEntity orderEntity) {
        OrderEntity orderEntityToUpdate = getOrderById(id);
        orderEntityToUpdate.setCreatedDate(orderEntity.getCreatedDate());
        orderEntityToUpdate.setStatus(orderEntity.getStatus());
        return orderRepository.save(orderEntityToUpdate);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
