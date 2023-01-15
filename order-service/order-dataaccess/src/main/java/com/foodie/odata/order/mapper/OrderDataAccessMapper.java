package com.foodie.odata.order.mapper;

import com.foodie.common.entity.Product;
import com.foodie.common.valueobject.CustomerId;
import com.foodie.common.valueobject.Money;
import com.foodie.common.valueobject.ProductId;
import com.foodie.common.valueobject.RestaurantId;
import com.foodie.odata.order.entity.OrderAddressEntity;
import com.foodie.odata.order.entity.OrderEntity;
import com.foodie.odata.order.entity.OrderItemEntity;
import com.foodie.odo.core.entity.Order;
import com.foodie.odo.core.entity.OrderItem;
import com.foodie.odo.core.valueobject.OrderId;
import com.foodie.odo.core.valueobject.StreetAddress;
import com.foodie.odo.core.valueobject.TrackingId;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDataAccessMapper {
    public OrderEntity orderToOrderEntity(Order order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId().getT())
                .customerId(order.getCustomerId().getT())
                .restaurantId(order.getRestaurantId().getT())
                .address(streetAddressToOrderAddressEntity(order.getAddress()))
                .price(order.getPrice().getPrice())
                .trackingId(order.getTrackingId().getT())
                .orderStatus(order.getOrderStatus())
                .items(orderItemToOrderItemEntity(order.getItems()))
                .failureMessages(order.getFailureMessages()!=null?
                        String.join(",",order.getFailureMessages()):"")
                .build();
        orderEntity.getAddress().setOrderEntity(orderEntity);
        orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrderEntity(orderEntity));
        return orderEntity;
    }

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        Order.builder()
                .id(new OrderId(orderEntity.getId()))
                .customerId(new CustomerId(orderEntity.getCustomerId()))
                .restaurantId(new RestaurantId(orderEntity.getRestaurantId()))
                .address(orderAddressEntityToStreetAddress(orderEntity.getAddress()))
                .price(new Money(orderEntity.getPrice()))
                .trackingId(new TrackingId(orderEntity.getTrackingId()))
                .orderStatus(orderEntity.getOrderStatus())
                .items(orderItemEntityToOrderItem(orderEntity.getItems()))
                .failureMessages(orderEntity.getFailureMessages().isEmpty()?new ArrayList<>():
                        new ArrayList<>(Arrays.asList(orderEntity.getFailureMessages().split(","))))
                .build();
        return null;
    }

    private List<OrderItem> orderItemEntityToOrderItem(List<OrderItemEntity> items) {
        return items.stream().map(orderItemEntity -> OrderItem.builder()
                .orderId(new OrderId(orderItemEntity.getOrderEntity().getId()))
                .product(new Product(new ProductId(orderItemEntity.getProductId())))
                .quantity(orderItemEntity.getQuantity())
                .price(new Money(orderItemEntity.getPrice()))
                .subtotal(new Money(orderItemEntity.getSubtotal()))
                .build()).collect(Collectors.toList());
    }

    private StreetAddress orderAddressEntityToStreetAddress(OrderAddressEntity address) {
        return new StreetAddress(address.getId(), address.getStreet(), address.getPostalCode(), address.getCity());
    }

    private List<OrderItemEntity> orderItemToOrderItemEntity(List<OrderItem> items) {
        List<OrderItemEntity> orderItemEntities = items.stream().map(orderItem ->
                OrderItemEntity.builder()
                        .id(orderItem.getId().getT())
                        .productId(orderItem.getProduct().getId().getT())
                        .subtotal(orderItem.getSubtotal().getPrice())
                        .quantity(orderItem.getQuantity())
                        .price(orderItem.getPrice().getPrice())
                        .build()).collect(Collectors.toList());
        return orderItemEntities;
    }

    private OrderAddressEntity streetAddressToOrderAddressEntity(StreetAddress address) {
    return OrderAddressEntity.builder()
            .id(address.getT())
            .street(address.getStreet())
            .city(address.getCity())
            .postalCode(address.getPostalCode())
            .build();
    }


}
