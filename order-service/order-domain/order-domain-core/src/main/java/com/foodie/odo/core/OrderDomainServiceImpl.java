package com.foodie.odo.core;

import com.foodie.common.entity.Product;
import com.foodie.odo.core.entity.Order;
import com.foodie.odo.core.entity.Restaurant;
import com.foodie.odo.core.event.OrderCreatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OrderDomainServiceImpl implements OrderDomainService{

    private final Order order;

    public OrderDomainServiceImpl(Order order) {
        this.order = order;
    }
    @Override
    public OrderCreatedEvent preSaveOrderValidationAndInitialization(Order order, Restaurant restaurant) {
        order.validateOrder(order);
        setOrderItemProductInformation(order, restaurant);
        order.initializeOrder();
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    private void setOrderItemProductInformation(Order order, Restaurant restaurant) {
        order.getItems().stream().forEach(orderItem -> {
            restaurant.getProducts().stream().forEach(product -> {
                Product currentProduct = orderItem.getProduct();
                if(currentProduct.equals(product)){
                    currentProduct.setName(product.getName());
                    currentProduct.setPrice(product.getPrice());
                }
            });
        });
    }
}
