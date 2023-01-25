package com.foodie.odo.appservice.ports.output.publisher.restaurant;

import com.foodie.common.event.DomainEventPublisher;
import com.foodie.odo.core.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestPublisher extends DomainEventPublisher<OrderPaidEvent> {
}
