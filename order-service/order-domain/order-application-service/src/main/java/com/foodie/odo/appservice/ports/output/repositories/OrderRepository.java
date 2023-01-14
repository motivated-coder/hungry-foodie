package com.foodie.odo.appservice.ports.output.repositories;

import com.foodie.odo.core.event.OrderCreatedEvent;
import com.foodie.odo.core.entity.Order;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order saveOrder(Order order);

    Optional<Order> findByTrackingID(UUID trackingId);
}
