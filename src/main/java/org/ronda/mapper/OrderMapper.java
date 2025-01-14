package org.ronda.mapper;

import org.ronda.dto.OrderDto;
import org.ronda.db.model.Guest;
import org.ronda.db.model.OrderState;
import org.ronda.db.model.Order;
import org.ronda.db.repository.OrderStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    @Autowired
    private OrderStateRepository orderStateRepository;


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
            dto.setOrderState(order.getState().getDescription());
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
            state.setDescription(orderDto.getOrderState());
            order.setState(state);
        }

        return order;
    }

    public OrderState toOrderStateEntity(String orderStateString) {
        if (orderStateString == null) {
            return null;
        }

        return orderStateRepository.findByDescription(orderStateString)
                .orElseThrow(() -> new IllegalArgumentException("Unknown order state: " + orderStateString));
    }
}
