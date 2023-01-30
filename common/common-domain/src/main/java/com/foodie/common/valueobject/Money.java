package com.foodie.common.valueobject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    private final BigDecimal price;
    public static final Money ZERO = new Money(BigDecimal.ZERO);

    public Money(BigDecimal price) {
        this.price = price;
    }

    public boolean isGreaterThanZero() {
        return this.price != null && this.price.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isGreaterThan(Money money) {
        return this.price != null && this.price.compareTo(money.getPrice()) > 0;
    }

    public Money add(Money money) {
        return new Money(setScale(this.price.add(money.getPrice())));
    }

    public Money subtract(Money money) {
        return new Money(setScale(this.price.subtract(money.getPrice())));
    }

    public Money multiply(int multiplier) {
        return new Money(setScale(this.price.multiply(new BigDecimal(multiplier))));
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return price.equals(money.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price);
    }

    private BigDecimal setScale(BigDecimal input) {
        return input.setScale(2, 0);
    }
}
