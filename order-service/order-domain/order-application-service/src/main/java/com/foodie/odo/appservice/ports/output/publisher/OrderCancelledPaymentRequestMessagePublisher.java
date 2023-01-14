package com.foodie.odo.appservice.ports.output.publisher;

import com.foodie.common.event.DomainEventPublisher;
import com.foodie.odo.core.event.OrderCreatedEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {}
