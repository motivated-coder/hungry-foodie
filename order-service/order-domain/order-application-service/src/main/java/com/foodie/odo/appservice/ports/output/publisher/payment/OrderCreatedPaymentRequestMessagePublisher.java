package com.foodie.odo.appservice.ports.output.publisher.payment;

import com.foodie.common.event.DomainEventPublisher;
import com.foodie.odo.core.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {}
