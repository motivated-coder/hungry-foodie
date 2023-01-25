package com.foodie.odo.core.event;

import com.foodie.common.event.DomainEvent;
import com.foodie.odo.core.entity.Order;

import java.time.ZonedDateTime;


public class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime zonedDateTime;

    public OrderEvent(Order order, ZonedDateTime zonedDateTime) {
        this.order = order;
        this.zonedDateTime = zonedDateTime;
    }

    public Order getOrder() {
        return order;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
