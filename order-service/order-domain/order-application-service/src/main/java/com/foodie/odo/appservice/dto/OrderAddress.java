package com.foodie.odo.appservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class OrderAddress {
    private final String street;
    private final String postalCode;
    private final String city;
}
