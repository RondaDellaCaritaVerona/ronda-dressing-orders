package org.ronda.service;

import org.ronda.dto.OrderDto;
import org.ronda.model.Guest;
import org.ronda.model.Order;
import org.ronda.model.OrderState;
import org.ronda.repository.OrderRepository;
import org.ronda.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<OrderDto> getFilteredOrders(String cardNumber, String orderState, String fromDate, String toDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // Retrieve all orders from the repository
        List<Order> orders = orderRepository.findAll();

        // Apply filters dynamically
        return orders.stream()
                // Filter by card number (guestId)
                .filter(order -> cardNumber == null || 
                        (order.getGuest() != null && order.getGuest().getCardNumber() != null 
                        && order.getGuest().getCardNumber().equals(cardNumber)))
                // Filter by order state
                .filter(order -> orderState == null || order.getState().getName().equalsIgnoreCase(orderState))
                // Filter by date range
                .filter(order -> {
                    if (fromDate == null && toDate == null) return true;
                    try {
                        Date from = fromDate != null ? formatter.parse(fromDate) : null;
                        Date to = toDate != null ? formatter.parse(toDate) : null;
                        return (from == null || !order.getOrderDate().before(from)) &&
                               (to == null || !order.getOrderDate().after(to));
                    } catch (ParseException e) {
                        return false; // Ignore invalid dates
                    }
                })
                // Map to DTO
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));
        return orderMapper.toDto(order);
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDto updateOrder(String orderId, OrderDto orderDto) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with ID: " + orderId));

        // Update fields from the DTO
        existingOrder.setOrderDate(orderDto.getOrderDate());

        // Update the order state
        if (orderDto.getOrderState() != null) {
            OrderState orderState = orderMapper.toOrderStateEntity(orderDto.getOrderState());
            existingOrder.setState(orderState);
        }

        // Update the guest using guestId (instead of guestName)
        if (orderDto.getGuestId() != null) {
            Guest guest = new Guest();
            guest.setCardNumber(orderDto.getGuestId());  // Using guestId
            existingOrder.setGuest(guest);  // You may fetch the full guest entity from DB if needed
        }

        // Save and return the updated order
        Order updatedOrder = orderRepository.save(existingOrder);
        return orderMapper.toDto(updatedOrder);
    }

    @Override
    public void deleteOrder(String orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
        orderRepository.deleteById(orderId);
    }
}
