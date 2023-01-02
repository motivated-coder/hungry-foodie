package com.foodie.odo.appservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class OrderDto {
    private final UUID customerId;
    private final UUID restaurantId;
    private final BigDecimal price;
    private final List<OrderItem> items;
    private final OrderAddress address;
}
