package com.dutra.ordertest.service;

import com.dutra.ordertest.dto.OrderDTO;
import com.dutra.ordertest.dto.OrderItemDTO;
import com.dutra.ordertest.model.Order;
import com.dutra.ordertest.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    public OrderServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateOrderSuccessfully() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setExternalOrderId(123L);
        orderDTO.setItems(List.of(new OrderItemDTO(1, 2,  BigDecimal.valueOf(10))));

        when(orderRepository.findByExternalOrderId(123L)).thenReturn(Optional.empty());
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order createdOrder = orderService.createOrder(orderDTO);

        assertEquals(123L, createdOrder.getExternalOrderId());
        assertEquals(BigDecimal.valueOf(20), createdOrder.getTotalAmount());
        assertEquals("CALCULATED", createdOrder.getStatus());
        assertNotNull(createdOrder.getCreatedAt());

        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void shouldThrowExceptionWhenDuplicateOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setExternalOrderId(2L);

        when(orderRepository.findByExternalOrderId(2L))
                .thenReturn(Optional.of(new Order()));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder(orderDTO)
        );

        assertEquals("duplicated order: 2", exception.getMessage());
        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    void shouldFindAllProcessedOrders() {
        List<Order> mockOrders = List.of(new Order(), new Order());
        when(orderRepository.findAll()).thenReturn(mockOrders);

        List<Order> orders = orderService.findAllProcessedOrders();
        assertEquals(2, orders.size());
        verify(orderRepository).findAll();
    }

    @Test
    void shouldCalculateCorrectTotalWhenCreatingOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setExternalOrderId(3L);
        orderDTO.setItems(List.of(
                new OrderItemDTO(1, 3, BigDecimal.valueOf(12)),
                new OrderItemDTO(2, 2, BigDecimal.valueOf(7.5))
        ));

        when(orderRepository.findByExternalOrderId(3L)).thenReturn(Optional.empty());
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order createdOrder = orderService.createOrder(orderDTO);
        assertEquals(BigDecimal.valueOf(51.0), createdOrder.getTotalAmount());
    }


}
