package com.foodie.odo.appservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class TrackingOrderQuery {
    @NotNull
    private final UUID trackingId;
}
