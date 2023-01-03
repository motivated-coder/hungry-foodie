package com.foodie.common.valueobject;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal price;

    public Money(BigDecimal price) {
        this.price = price;
    }
}
