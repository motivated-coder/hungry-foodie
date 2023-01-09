package com.foodie.odo.core;

import com.foodie.odo.core.entity.Order;
import com.foodie.odo.core.entity.OrderItem;
import com.foodie.odo.core.entity.Restaurant;
import com.foodie.odo.core.event.OrderCreatedEvent;

public interface OrderDomainService {
    OrderCreatedEvent preSaveOrderValidationAndInitialization(Order order, Restaurant restaurant);
}
