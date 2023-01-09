package com.foodie.odo.appservice.ports.output;

import com.foodie.odo.core.event.OrderCreatedEvent;
import com.foodie.odo.core.entity.Order;

public interface OrderRepository {
    Order saveOrder(Order order);
}
