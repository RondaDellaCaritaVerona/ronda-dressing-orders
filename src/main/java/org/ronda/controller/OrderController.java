package org.ronda.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.ronda.dto.OrderDto;
import org.ronda.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  // Get all orders
  @GetMapping
  public ResponseEntity<List<OrderDto>> getAllOrders(
      @RequestParam(required = false) String cardNumber,
      @RequestParam(required = false) String orderState,
      @RequestParam(required = false) String fromDate,
      @RequestParam(required = false) String toDate) {
    var orders = orderService.getFilteredOrders(cardNumber, orderState, fromDate, toDate);
    return ResponseEntity.ok(orders);
  }

  // Get order by ID
  @GetMapping("/{id}")
  public ResponseEntity<OrderDto> getOrderById(@PathVariable String id) {
<<<<<<< Updated upstream
    OrderDto order = orderService.getOrderById(id);
=======
    var order = orderService.getOrderById(id);
>>>>>>> Stashed changes
    return ResponseEntity.ok(order);
  }

  // Create a new order
  @PostMapping
  public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
<<<<<<< Updated upstream
    OrderDto createdOrder = orderService.createOrder(orderDto);
=======
    var createdOrder = orderService.createOrder(orderDto);
>>>>>>> Stashed changes
    return ResponseEntity.ok(createdOrder);
  }

  // Update an existing order
  @PutMapping("/{id}")
  public ResponseEntity<OrderDto> updateOrder(
      @PathVariable String id, @RequestBody OrderDto orderDto) {
<<<<<<< Updated upstream
    OrderDto updatedOrder = orderService.updateOrder(id, orderDto);
=======
    var updatedOrder = orderService.updateOrder(id, orderDto);
>>>>>>> Stashed changes
    return ResponseEntity.ok(updatedOrder);
  }

  // Delete an order
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
    orderService.deleteOrder(id);
    return ResponseEntity.noContent().build();
  }
}
