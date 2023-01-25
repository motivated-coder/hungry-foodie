package com.foodie.odo.core.event;

import com.foodie.odo.core.entity.Order;

import java.time.ZonedDateTime;

public class OrderRejectedEvent extends OrderEvent{
    public OrderRejectedEvent(Order order, ZonedDateTime zonedDateTime) {
        super(order, zonedDateTime);
    }
}
