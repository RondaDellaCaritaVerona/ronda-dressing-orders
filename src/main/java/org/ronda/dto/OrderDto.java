package org.ronda.dto;

import java.util.Date;
import lombok.Builder;

@Builder
public record OrderDto(String orderId, Date orderDate, String guestId, String orderState) {}
