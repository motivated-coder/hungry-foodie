package com.foodie.common.valueobject;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal price;
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal price) {
        this.price = price;
    }

    public boolean isGreaterThanZero() {
        return this.price != null && this.price.compareTo(BigDecimal.ZERO) > 0;
    }

    public Money add(Money money) {
        return new Money(this.price.add(money.price));
    }

    public BigDecimal getPrice() {
        return price;
    }
}
