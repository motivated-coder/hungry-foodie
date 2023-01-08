package com.foodie.odo.core;

import com.foodie.odo.core.entity.Order;
import com.foodie.odo.core.entity.OrderItem;
import com.foodie.odo.core.entity.Restaurant;

public interface OrderDomainService {
    void preSaveOrderValidation(Order order, Restaurant restaurant);
}
