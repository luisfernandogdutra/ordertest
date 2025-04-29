package com.dutra.ordertest.controller;

import com.dutra.ordertest.dto.OrderDTO;
import com.dutra.ordertest.model.Order;
import com.dutra.ordertest.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(name = "Order Controller", description = "endpoints for orders")
public class OrderController {

    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @Operation(
            summary = "create new order",
            description = "create new order",
            responses = {
                    @ApiResponse(responseCode = "201", description = "create order with success"),
                    @ApiResponse(responseCode = "400", description = "invalid data")
            }
    )
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Received request to create order: {}", orderDTO);
        Order order = orderService.createOrder(orderDTO);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    @Operation(
            summary = "list all order",
            description = "return list of order",
            responses = {
                    @ApiResponse(responseCode = "201", description = "get list orders"),
                    @ApiResponse(responseCode = "400", description = "invalid data")
            }
    )
    public ResponseEntity<List<Order>> getAllProcessedOrders() {
        List<Order> orders = orderService.findAllProcessedOrders();
        return ResponseEntity.ok(orders);
    }
}