package com.foodie.om.mapper;

import com.foodie.common.enums.OrderApprovalStatus;
import com.foodie.common.enums.PaymentStatus;
import com.foodie.kafka.order.avro.model.*;
import com.foodie.odo.appservice.dto.PaymentResponse;
import com.foodie.odo.appservice.dto.RestaurantResponse;
import com.foodie.odo.core.entity.Order;
import com.foodie.odo.core.event.OrderCancelledEvent;
import com.foodie.odo.core.event.OrderCreatedEvent;
import com.foodie.odo.core.event.OrderPaidEvent;

import java.util.UUID;
import java.util.stream.Collectors;

public class OrderMessagingMapper {
    public PaymentRequestAvroModel orderCreatedEventToPaymentRequestAvroModel(OrderCreatedEvent domainEvent) {
        Order order = domainEvent.getOrder();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setCustomerId(order.getCustomerId().getT().toString())
                .setOrderId(order.getId().getT().toString())
                .setPrice(order.getPrice().getPrice())
                .setCreatedAt(domainEvent.getZonedDateTime().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
                .build();
    }

    public PaymentRequestAvroModel orderCancelledEventToPaymentRequestAvroModel(OrderCancelledEvent domainEvent) {
        Order order = domainEvent.getOrder();
        return PaymentRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setCustomerId(order.getCustomerId().getT().toString())
                .setOrderId(order.getId().getT().toString())
                .setPrice(order.getPrice().getPrice())
                .setCreatedAt(domainEvent.getZonedDateTime().toInstant())
                .setPaymentOrderStatus(PaymentOrderStatus.PENDING)
                .build();
    }

    public RestaurantApprovalRequestAvroModel
    orderPaidEventToRestaurantApprovalRequestAvroModel(OrderPaidEvent orderPaidEvent) {
        Order order = orderPaidEvent.getOrder();
        return RestaurantApprovalRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID().toString())
                .setSagaId("")
                .setOrderId(order.getId().getT().toString())
                .setRestaurantId(order.getRestaurantId().getT().toString())
                .setOrderId(order.getId().getT().toString())
                .setRestaurantOrderStatus(RestaurantOrderStatus
                        .valueOf(order.getOrderStatus().name()))
                .setProducts(order.getItems().stream().map(orderItem ->
                       Product.newBuilder()
                                .setId(orderItem.getProduct().getId().getT().toString())
                                .setQuantity(orderItem.getQuantity())
                                .build()).collect(Collectors.toList()))
                .setPrice(order.getPrice().getPrice())
                .setCreatedAt(orderPaidEvent.getZonedDateTime().toInstant())
                .setRestaurantOrderStatus(RestaurantOrderStatus.PAID)
                .build();
    }

    public PaymentResponse paymentResponseAvroModelToPaymentResponse(PaymentResponseAvroModel paymentResponseAvroModel) {
        return PaymentResponse.builder()
                .id(paymentResponseAvroModel.getId())
                .sagaId(paymentResponseAvroModel.getSagaId())
                .paymentId(paymentResponseAvroModel.getPaymentId())
                .customerId(paymentResponseAvroModel.getCustomerId())
                .orderId(paymentResponseAvroModel.getOrderId())
                .price(paymentResponseAvroModel.getPrice())
                .createdAt(paymentResponseAvroModel.getCreatedAt())
                .paymentStatus(PaymentStatus.valueOf(paymentResponseAvroModel.getPaymentStatus().name()))
                .failureMessages(paymentResponseAvroModel.getFailureMessages())
                .build();
    }

    public RestaurantResponse approvalResponseAvroModelToApprovalResponse(RestaurantApprovalResponseAvroModel restaurantApprovalResponseAvroModel) {
        return RestaurantResponse.builder()
                .id(restaurantApprovalResponseAvroModel.getId())
                .sagaId(restaurantApprovalResponseAvroModel.getSagaId())
                .restaurantId(restaurantApprovalResponseAvroModel.getRestaurantId())
                .orderId(restaurantApprovalResponseAvroModel.getOrderId())
                .createdAt(restaurantApprovalResponseAvroModel.getCreatedAt())
                //TODO : check how to use enum
                .orderApprovalStatus(OrderApprovalStatus.valueOf(restaurantApprovalResponseAvroModel.getOrderApprovalStatus().name()))
                .failureMessages(restaurantApprovalResponseAvroModel.getFailureMessages())
                .build();
    }
}
