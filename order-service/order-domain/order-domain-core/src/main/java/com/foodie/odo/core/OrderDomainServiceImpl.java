package com.foodie.odo.core;

import com.foodie.common.entity.Product;
import com.foodie.odo.core.entity.Order;
import com.foodie.odo.core.entity.Restaurant;

public class OrderDomainServiceImpl implements OrderDomainService{

    private final Order order;

    public OrderDomainServiceImpl(Order order) {
        this.order = order;
    }
    @Override
    public void preSaveOrderValidation(Order order, Restaurant restaurant) {
        order.validateOrder(order);
        setOrderItemProductInformation(order, restaurant);
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
