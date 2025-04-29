package com.dutra.ordertest.controller;

import com.dutra.ordertest.dto.OrderDTO;
import com.dutra.ordertest.model.Order;
import com.dutra.ordertest.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Api(tags = "Order Controller", description = "endpoints for orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ApiOperation(value = "create new order")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "create order with success"),
            @ApiResponse(code = 400, message = "invalid data")
    })
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllProcessedOrders() {
        List<Order> orders = orderService.findAllProcessedOrders();
        return ResponseEntity.ok(orders);
    }
}