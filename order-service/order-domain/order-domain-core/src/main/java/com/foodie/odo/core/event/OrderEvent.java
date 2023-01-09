package com.foodie.odo.core.event;

import com.foodie.common.event.DomainEvent;
import com.foodie.odo.core.entity.Order;

import java.time.ZonedDateTime;


public class OrderEvent implements DomainEvent<Order> {
    private Order order;
    private ZonedDateTime zonedDateTime;

    public OrderEvent(Order order, ZonedDateTime zonedDateTime) {
        this.order = order;
        this.zonedDateTime = zonedDateTime;
    }
}
