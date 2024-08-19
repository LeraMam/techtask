package com.example.techtask.service.impl;

import com.example.techtask.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findTop1ByQuantityGreaterThanOrderByCreatedAtDesc(int quantity);

    @Query("SELECT o FROM Order o WHERE o.userId IN " +
            "(SELECT u.id FROM User u WHERE u.userStatus = 'ACTIVE') ORDER BY o.createdAt DESC")
    List<Order> findAllByUserStatusByCreatedAtDesc();

    @Query("SELECT o FROM Order o WHERE YEAR(o.createdAt) = :year AND o.orderStatus = 'PAID'")
    List<Order> findByCreatedAtAndOrderStatus(@Param("year") int year);
}
