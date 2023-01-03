package com.foodie.odo.core.entity;

import com.foodie.common.entity.AggregateRoot;
import com.foodie.common.enums.OrderStatus;
import com.foodie.common.valueobject.CustomerId;
import com.foodie.common.valueobject.Money;
import com.foodie.common.valueobject.RestaurantId;
import com.foodie.odo.core.valueobject.OrderId;
import com.foodie.odo.core.valueobject.StreetAddress;
import com.foodie.odo.core.valueobject.TrackingId;

import java.util.List;

public class Order extends AggregateRoot<OrderId> {
    CustomerId customerId;
    RestaurantId restaurantId;
    StreetAddress address;
    Money price;
    List<OrderItem> items;
    TrackingId trackingId;
    OrderStatus orderStatus;
    List<String> failureMessages;

    private Order(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        address = builder.address;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderId id;
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private StreetAddress address;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder address(StreetAddress val) {
            address = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
