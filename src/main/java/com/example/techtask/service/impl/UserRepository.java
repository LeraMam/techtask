package com.example.techtask.service.impl;

import com.example.techtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.orders o WHERE o.orderStatus = 'DELIVERED' AND " +
            "YEAR(o.createdAt) = :year " +
            "GROUP BY u.id " +
            "ORDER BY SUM(o.price * o.quantity) DESC LIMIT 1")
    Optional<User> findByTopOrderPriceAndYear(@Param("year") int year);
}
