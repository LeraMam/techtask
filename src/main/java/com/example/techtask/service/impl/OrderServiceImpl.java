package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order findOrder() {
        Optional<Order> order = orderRepository.findTop1ByQuantityGreaterThanOrderByCreatedAtDesc(1);
        return order.orElseGet(Order::new);
    }

    @Override
    public List<Order> findOrders() {
        return orderRepository.findAllByUserStatusByCreatedAtDesc();
    }
}
