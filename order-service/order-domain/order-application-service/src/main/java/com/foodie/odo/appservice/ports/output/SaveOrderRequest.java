package com.foodie.odo.appservice.ports.output;

import com.foodie.odo.appservice.event.OrderCreatedEvent;
import com.foodie.odo.core.entity.Order;

public interface SaveOrderRequest {

    OrderCreatedEvent saveOrder(Order order);
}
