package org.ronda.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.ronda.db.model.Guest;
import org.ronda.db.model.Order;
import org.ronda.db.model.OrderState;
import org.ronda.db.repository.OrderRepository;
import org.ronda.db.repository.OrderStateRepository;
import org.ronda.dto.OrderDto;
import org.ronda.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final OrderStateRepository orderStateRepository;

  @Override
  public List<OrderDto> getFilteredOrders(
      String cardNumber, String orderState, String fromDate, String toDate) {
    var formatter = new SimpleDateFormat("yyyy-MM-dd");

    // Retrieve all orders from the repository
    var orders = orderRepository.findAll();

    // Apply filters dynamically
    return orders.stream() // TODO this must be moved to sql otherwise is very inefficient
        // Filter by card number (guestId)
        .filter(
            order ->
                cardNumber == null
                    || (order.getGuest() != null
                        && order.getGuest().getCardNumber() != null
                        && order.getGuest().getCardNumber().equals(cardNumber)))
        // Filter by order state
        .filter(
            order ->
                orderState == null
                    || order.getState().getDescription().equalsIgnoreCase(orderState))
        // Filter by date range
        .filter(
            order -> {
              if (fromDate == null && toDate == null) {
                return true;
              }
              try {
                Date from = fromDate != null ? formatter.parse(fromDate) : null;
                Date to = toDate != null ? formatter.parse(toDate) : null;
                return (from == null || !order.getOrderDate().before(from))
                    && (to == null || !order.getOrderDate().after(to));
              } catch (ParseException e) {
                return false; // Ignore invalid dates
              }
            })
        // Map to DTO
        .map(OrderMapper::toDto)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toList());
  }

  @Override
  public OrderDto getOrderById(String orderId) {
    return orderRepository
        .findById(orderId)
        .flatMap(OrderMapper::toDto)
        .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
  }

  @Override
  public OrderDto createOrder(OrderDto orderDto) {
    return OrderMapper.toEntity(orderDto)
        .map(orderRepository::save)
        .flatMap(OrderMapper::toDto)
        .orElseThrow(() -> new RuntimeException("Error saving order ID: " + orderDto.orderId()));
  }

  @Override
  public OrderDto updateOrder(String orderId, OrderDto orderDto) {
    var existingOrder =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

    // Update fields from the DTO
    existingOrder.setOrderDate(orderDto.orderDate());

    // Update the order state
    Optional.ofNullable(orderDto.orderState())
        .ifPresent(state -> existingOrder.setState(toOrderStateEntity(state)));

    // Update the guest using guestId (instead of guestName)
    Optional.ofNullable(orderDto.guestId()) // You may fetch the full guest entity from DB if needed
        .ifPresent(
            cardNumber -> existingOrder.setGuest(Guest.builder().cardNumber(cardNumber).build()));

    // Save and return the updated order
    Order updatedOrder = orderRepository.save(existingOrder);
    return OrderMapper.toDto(updatedOrder)
        .orElseThrow(() -> new RuntimeException("Unable to update order with ID: " + orderId));
  }

  @Override
  public void deleteOrder(String orderId) {
    if (!orderRepository.existsById(orderId)) {
      throw new RuntimeException("Order not found with ID: " + orderId);
    }
    orderRepository.deleteById(orderId);
  }

  public OrderState toOrderStateEntity(String orderStateString) {
    return orderStateRepository
        .findByDescription(orderStateString)
        .orElseThrow(
            () -> new IllegalArgumentException("Unknown order state: " + orderStateString));
  }
}
