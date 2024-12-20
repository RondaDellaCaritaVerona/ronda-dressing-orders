package org.ronda.mapper;

import org.ronda.dto.OrderDto;
import org.ronda.model.Guest;
import org.ronda.model.Order;
import org.ronda.model.OrderState;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    // Map Order entity to OrderDto
    public OrderDto toDto(Order order) {
        if (order == null) {
            return null;
        }
        
        OrderDto dto = new OrderDto();
        dto.setOrderId(order.getOrderId());
        dto.setOrderDate(order.getOrderDate());
        
        // Map Guest to guestId (cardNumber)
        if (order.getGuest() != null) {
            dto.setGuestId(order.getGuest().getCardNumber());  // Map cardNumber to guestId
        }

        // Map OrderState to state name
        if (order.getState() != null) {
            dto.setOrderState(order.getState().getName());
        }

        return dto;
    }

    // Map OrderDto to Order entity
    public Order toEntity(OrderDto orderDto) {
        if (orderDto == null) {
            return null;
        }

        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setOrderDate(orderDto.getOrderDate());

        // Convert guestId (cardNumber) into a Guest object
        if (orderDto.getGuestId() != null) {
            Guest guest = new Guest();
            guest.setCardNumber(orderDto.getGuestId());  // Set cardNumber based on guestId
            order.setGuest(guest);
        }

        // Convert orderState to OrderState object
        if (orderDto.getOrderState() != null) {
            OrderState state = new OrderState();
            state.setName(orderDto.getOrderState());
            order.setState(state);
        }

        return order;
    }
}
