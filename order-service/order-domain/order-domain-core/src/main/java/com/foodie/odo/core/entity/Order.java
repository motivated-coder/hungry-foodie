package com.foodie.odo.core.entity;

import com.foodie.common.entity.AggregateRoot;
import com.foodie.common.enums.OrderStatus;
import com.foodie.common.valueobject.CustomerId;
import com.foodie.common.valueobject.Money;
import com.foodie.common.valueobject.RestaurantId;
import com.foodie.odo.core.exception.OrderDomainException;
import com.foodie.odo.core.valueobject.OrderId;
import com.foodie.odo.core.valueobject.OrderItemId;
import com.foodie.odo.core.valueobject.StreetAddress;
import com.foodie.odo.core.valueobject.TrackingId;

import java.util.List;
import java.util.UUID;

public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final StreetAddress address;
    private final Money price;
    private final List<OrderItem> items;
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Order(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        address = builder.address;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderId id;
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private StreetAddress address;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder address(StreetAddress val) {
            address = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

    public void validateOrder(Order order){
        validateOrderStatus(order);
        validateOrderPrice(order);
        validateOrderItem(order);
    }

    public void initializeOrder(){
        super.setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus=OrderStatus.PENDING;
        initializeOrderItems();
    }

    private void initializeOrderItems() {
        long itemId = 1;
        for(OrderItem item: items){
            item.initialize(super.getId(), new OrderItemId(itemId++));
        }
    }

    private void validateOrderPrice(Order order) {
        if(!order.price.isGreaterThanZero() && order.price==null){
            throw new OrderDomainException("Order price canot be 0 or null");
        }
    }

    private void validateOrderItem(Order order) {
        Money subtotal = order.items.stream().map(orderItem -> {
//            validateItemPrice(orderItem);
            orderItem.validateOrderItemPrice();
            return orderItem.getSubtotal();
        }).reduce(Money.ZERO,Money::add);

        if(!order.price.equals(subtotal)){
            throw new OrderDomainException("Order price doesn't match with items subtotal");
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().getPrice() +
                    " is not valid for product " + orderItem.getProduct().getId().getT());
        }
    }

    private void validateOrderStatus(Order order) {
        if(order.orderStatus!= null && order.getId()!=null){
            throw new OrderDomainException("Order is not in correct state before initialization");
        }
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public StreetAddress getAddress() {
        return address;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(TrackingId trackingId) {
        this.trackingId = trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<String> getFailureMessages() {
        return failureMessages;
    }

    public void setFailureMessages(List<String> failureMessages) {
        this.failureMessages = failureMessages;
    }
}
