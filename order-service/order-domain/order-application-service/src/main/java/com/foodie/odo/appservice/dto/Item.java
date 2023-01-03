package com.foodie.odo.appservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class Item {
    private final UUID productId;
    private final Integer quantity;
    private final BigDecimal price;
    private final BigDecimal subtotal;
}
