package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import com.example.techtask.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUser() {
        //TODO: Возвращать пользователя с максимальной общей суммой товаров, доставленных в 2003
        String query = "SELECT u FROM User u JOIN u.orders o WHERE o.orderStatus = 'DELIVERED' AND " +
                "YEAR(o.createdAt) = 2023 " +
                "GROUP BY u.id " +
                "ORDER BY SUM(o.price * o.quantity) DESC LIMIT 1";
        TypedQuery<User> q = entityManager.createQuery(query, User.class);
        return q.getSingleResult();
    }

    @Override
    public List<User> findUsers() {
        //TODO: Возвращать пользователей у которых есть оплаченные заказы в 2010.
        String query = "SELECT u FROM User u JOIN u.orders o WHERE o.orderStatus = 'PAID' AND " +
                "YEAR(o.createdAt) = 2010";
        TypedQuery<User> q = entityManager.createQuery(query, User.class);
        return q.getResultList();
    }
}
