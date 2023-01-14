package com.foodie.odo.appservice.dto;

import com.foodie.common.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Data
public class TrackOrderResponse {
private final OrderStatus orderStatus;
private final List<String> failureMessages;
private final UUID trackingId;
}
