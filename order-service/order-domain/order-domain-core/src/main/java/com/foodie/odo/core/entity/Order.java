package com.foodie.odo.core.entity;

import com.foodie.common.entity.AggregateRoot;
import com.foodie.common.enums.OrderStatus;
import com.foodie.common.valueobject.CustomerId;
import com.foodie.common.valueobject.RestaurantId;
import com.foodie.common.valueobject.Money;
import com.foodie.odo.core.valueobject.OrderId;
import com.foodie.odo.core.valueobject.StreetAddress;
import com.foodie.odo.core.valueobject.TrackingId;

import java.util.List;

public class Order extends AggregateRoot<OrderId> {
    CustomerId customerId;
    RestaurantId restaurantId;
    StreetAddress address;
    Money price;
    List<OrderItem> items;
    TrackingId trackingId;
    OrderStatus orderStatus;
    List<String> failureMessages;
}
