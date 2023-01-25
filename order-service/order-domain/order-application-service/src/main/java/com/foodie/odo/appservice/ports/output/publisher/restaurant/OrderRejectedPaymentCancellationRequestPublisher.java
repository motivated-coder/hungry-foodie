package com.foodie.odo.appservice.ports.output.publisher.restaurant;

import com.foodie.common.event.DomainEventPublisher;
import com.foodie.odo.core.event.OrderRejectedEvent;

public interface OrderRejectedPaymentCancellationRequestPublisher extends DomainEventPublisher<OrderRejectedEvent> {}
