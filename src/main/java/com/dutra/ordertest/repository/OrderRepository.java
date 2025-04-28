package com.dutra.ordertest.repository;

import com.dutra.ordertest.model.Order;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByExternalOrderId(String externalOrderId);
}
