package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public User findUser() {
        return userRepository.findByTopOrderPriceAndYear(2003).orElseGet(User::new);
    }

    @Override
    public List<User> findUsers() {
        List<User> users = userRepository.findAll();
        List<Order> orders = orderRepository.findByCreatedAtAndOrderStatus(2010);
        return users.stream()
                .filter(user -> user.getOrders()
                        .stream().anyMatch(orders::contains))
                .collect(Collectors.toList());
    }
}
