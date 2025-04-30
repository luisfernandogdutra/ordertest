package com.dutra.ordertest.repository;

import com.dutra.ordertest.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByExternalOrderId(Long externalOrderId);
    List<Order> findByStatus(String status);
}
