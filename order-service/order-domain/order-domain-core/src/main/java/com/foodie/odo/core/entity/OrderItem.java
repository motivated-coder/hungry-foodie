package com.foodie.odo.core.entity;

import com.foodie.common.entity.BaseEntity;
import com.foodie.common.entity.Product;
import com.foodie.common.valueobject.Money;
import com.foodie.odo.core.valueobject.OrderId;
import com.foodie.odo.core.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {
    OrderId orderId;
    Product product;
    Integer quantity;
    Money price;
    Money subtotal;
}
