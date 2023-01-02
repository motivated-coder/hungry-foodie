package com.foodie.odo.appservice.dto;

import com.foodie.common.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class OrderDtoResponse {
    private final UUID trackingId;
    private final OrderStatus orderStatus;
    private final String message;
}
