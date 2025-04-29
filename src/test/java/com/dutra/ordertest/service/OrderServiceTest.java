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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        orderDTO.setExternalOrderId(10L);

        OrderItemDTO orderItemDTO1 = new OrderItemDTO();
        orderItemDTO1.setProductCode(1);
        orderItemDTO1.setQuantity(2);
        orderItemDTO1.setPrice(BigDecimal.TEN);

        orderDTO.setItems(List.of(orderItemDTO1));
        orderService.createOrder(orderDTO);

        verify(orderRepository, times(1)).save(any(Order.class));
    }
}
