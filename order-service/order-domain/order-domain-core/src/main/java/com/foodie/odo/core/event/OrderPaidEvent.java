package com.foodie.odo.core.event;

import com.foodie.odo.core.entity.Order;

import java.time.ZonedDateTime;

public class OrderPaidEvent extends OrderEvent {
    public OrderPaidEvent(Order order, ZonedDateTime zonedDateTime) {
        super(order, zonedDateTime);
    }
}
