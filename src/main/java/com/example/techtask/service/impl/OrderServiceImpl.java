package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import com.example.techtask.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order findOrder() {
        String query = "SELECT o FROM Order o WHERE o.quantity>1 ORDER BY o.createdAt DESC LIMIT 1";
        TypedQuery<Order> q = entityManager.createQuery(query, Order.class);
        return q.getSingleResult();
    }

    @Override
    public List<Order> findOrders() {
        String query = "SELECT o FROM Order o WHERE o.userId IN (SELECT u.id FROM User u " +
                "WHERE u.userStatus = 'ACTIVE') ORDER BY o.createdAt DESC";
        TypedQuery<Order> q = entityManager.createQuery(query, Order.class);
        return q.getResultList();
    }
}
