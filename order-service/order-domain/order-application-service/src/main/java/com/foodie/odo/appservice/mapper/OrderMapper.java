package com.foodie.odo.appservice.mapper;

import com.foodie.common.entity.Product;
import com.foodie.common.enums.OrderStatus;
import com.foodie.common.valueobject.CustomerId;
import com.foodie.common.valueobject.Money;
import com.foodie.common.valueobject.ProductId;
import com.foodie.common.valueobject.RestaurantId;
import com.foodie.odo.appservice.dto.*;
import com.foodie.odo.core.entity.Order;
import com.foodie.odo.core.entity.OrderItem;
import com.foodie.odo.core.valueobject.OrderId;
import com.foodie.odo.core.valueobject.OrderItemId;
import com.foodie.odo.core.valueobject.StreetAddress;
import com.foodie.odo.core.valueobject.TrackingId;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderMapper {
    public Order orderDtoToOrder(OrderDto orderDto){
        return Order.builder()
                .customerId(new CustomerId(orderDto.getCustomerId()))
                .restaurantId(new RestaurantId(orderDto.getRestaurantId()))
                .address(orderAddressToStreetAddress(orderDto.getAddress()))
                .price(new Money(orderDto.getPrice()))
                .items(itemToOrderItem(orderDto.getItems()))
                .build();

    }

    private List<OrderItem> itemToOrderItem(List<Item> items) {
        return items.stream().map(item ->
            OrderItem.builder()
                    .price(new Money(item.getPrice()))
                    .quantity(item.getQuantity())
                    .product(new Product(new ProductId(item.getProductId())))
                    .subtotal(new Money(item.getSubtotal()))
                    .build()
        ).collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress address) {
        return new StreetAddress(UUID.randomUUID(),
                address.getStreet(),address.getPostalCode(), address.getCity());
    }

    public OrderDtoResponse orderToOrderDtoResponse(Order order){
        return OrderDtoResponse.builder()
                .orderStatus(order.getOrderStatus())
                .trackingId(order.getTrackingId().getT())
                .message("Order created successfully")
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Optional<Order> order) {
        return TrackOrderResponse.builder()
                .orderStatus(order.get().getOrderStatus())
                .trackingId(order.get().getTrackingId().getT())
                .failureMessages(order.get().getFailureMessages())
                .build();
    }
}
