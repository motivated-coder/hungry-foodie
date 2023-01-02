package com.foodie.odo.appservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class TrackingOrderQuery {
    private final UUID trackingId;
}
