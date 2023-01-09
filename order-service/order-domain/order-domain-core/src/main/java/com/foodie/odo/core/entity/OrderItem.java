package com.foodie.odo.core.entity;

import com.foodie.common.entity.BaseEntity;
import com.foodie.common.entity.Product;
import com.foodie.common.valueobject.Money;
import com.foodie.odo.core.exception.OrderDomainException;
import com.foodie.odo.core.valueobject.OrderId;
import com.foodie.odo.core.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {
    OrderId orderId;
    private final Product product;
    private final Integer quantity;
    private final Money price;
    private final Money subtotal;

    private OrderItem(Builder builder) {
        super.setId(builder.id);
        orderId = builder.orderId;
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subtotal = builder.subtotal;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initialize(OrderId orderId, OrderItemId orderItemId) {
        this.orderId=orderId;
        super.setId(orderItemId);
    }

    public static final class Builder {
        private OrderItemId id;
        private OrderId orderId;
        private Product product;
        private Integer quantity;
        private Money price;
        private Money subtotal;

        private Builder() {
        }

        public Builder id(OrderItemId val) {
            id = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subtotal(Money val) {
            subtotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
    protected void validateOrderItemPrice() {
        if(!this.price.isGreaterThanZero()){
            throw new OrderDomainException("Order Items price cannot be null or less than 0");
        }
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubtotal() {
        return subtotal;
    }
}
