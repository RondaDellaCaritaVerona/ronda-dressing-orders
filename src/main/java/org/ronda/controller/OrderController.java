package org.ronda.controller;

import org.ronda.dto.OrderDto;
import org.ronda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Get all orders
    @GetMapping
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(
            @RequestParam(required = false) String cardNumber,
            @RequestParam(required = false) String orderState,
            @RequestParam(required = false) String fromDate,
            @RequestParam(required = false) String toDate
    ) {
        List<OrderDto> orders = orderService.getFilteredOrders(cardNumber, orderState, fromDate, toDate);
        return ResponseEntity.ok(orders);
    }

    // Get order by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String id) {
        OrderDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // Create a new order
    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(createdOrder);
    }

    // Update an existing order
    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable String id, @RequestBody OrderDto orderDto) {
        OrderDto updatedOrder = orderService.updateOrder(id, orderDto);
        return ResponseEntity.ok(updatedOrder);
    }

    // Delete an order
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
