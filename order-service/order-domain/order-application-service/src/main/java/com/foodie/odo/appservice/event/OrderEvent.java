package com.foodie.odo.appservice.event;

import com.foodie.common.event.DomainEvent;
import com.foodie.odo.core.entity.Order;

import java.time.ZonedDateTime;


public class OrderEvent implements DomainEvent<Order> {
    Order order;
    ZonedDateTime zonedDateTime;
}
