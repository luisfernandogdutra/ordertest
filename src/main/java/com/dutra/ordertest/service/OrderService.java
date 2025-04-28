package com.dutra.ordertest.service;

import com.dutra.ordertest.dto.OrderDTO;
import com.dutra.ordertest.model.Order;
import com.dutra.ordertest.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(OrderDTO orderDTO) {
        orderRepository.findByExternalOrderId(orderDTO.getExternalOrderId()).ifPresent(order -> {
            throw new IllegalArgumentException("Pedido duplicado: " + orderDTO.getExternalOrderId());
        });

        Order order = new Order();
        order.setExternalOrderId(orderDTO.getExternalOrderId());
        order.setTotalAmount(calculateTotal(orderDTO));
        order.setStatus("CALCULATED");
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    private BigDecimal calculateTotal(OrderDTO orderDTO) {
        return orderDTO.getItems().stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Order> findAllProcessedOrders() {
        return orderRepository.findAll();
    }
}

