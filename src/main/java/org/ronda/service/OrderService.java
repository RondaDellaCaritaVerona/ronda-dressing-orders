package org.ronda.service;

import java.util.List;
import org.ronda.dto.OrderDto;

public interface OrderService {

  // Retrieve all orders with optional filters
  List<OrderDto> getFilteredOrders(
      String guestId, String orderState, String fromDate, String toDate);

  // Retrieve an order by ID
  OrderDto getOrderById(String orderId);

  // Create a new order
  OrderDto createOrder(OrderDto orderDto);

  // Update an existing order
  OrderDto updateOrder(String orderId, OrderDto orderDto);

  // Delete an order
  void deleteOrder(String orderId);
}
