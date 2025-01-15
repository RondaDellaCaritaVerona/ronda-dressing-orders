package org.ronda.mapper;

import java.util.Objects;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.ronda.db.model.Guest;
import org.ronda.db.model.Order;
import org.ronda.db.model.OrderState;
import org.ronda.dto.OrderDto;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderMapper {

  // Map Order entity to OrderDto
  public static Optional<OrderDto> toDto(Order order) {
    if (Objects.isNull(order)) {
      return Optional.empty();
    }

    var dtoBuilder = OrderDto.builder();
    dtoBuilder.orderId(order.getOrderId());
    dtoBuilder.orderDate(order.getOrderDate());

    // Map Guest to guestId (cardNumber)
    Optional.ofNullable(order.getGuest()).map(Guest::getCardNumber).ifPresent(dtoBuilder::guestId);

    // Map OrderState to state name
    Optional.ofNullable(order.getState())
        .map(OrderState::getDescription)
        .ifPresent(dtoBuilder::orderState);

    return Optional.of(dtoBuilder.build());
  }

  // Map OrderDto to Order entity
  public static Optional<Order> toEntity(OrderDto orderDto) {
    if (Objects.isNull(orderDto)) {
      return Optional.empty();
    }

    var order = new Order();
    order.setOrderId(orderDto.orderId());
    order.setOrderDate(orderDto.orderDate());

    // Convert guestId (cardNumber) into a Guest object
    Optional.ofNullable(orderDto.guestId())
        .ifPresent(guestId -> order.setGuest(Guest.builder().cardNumber(guestId).build()));

    // Convert orderState to OrderState object
    Optional.ofNullable(orderDto.orderState())
        .ifPresent(orderSate -> OrderState.builder().description(orderSate).build());

    return Optional.of(order);
  }
}
