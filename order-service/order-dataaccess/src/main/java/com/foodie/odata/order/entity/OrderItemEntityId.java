package com.foodie.odata.order.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderItemEntityId {
    private Long id;
    private OrderEntity orderEntity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemEntityId)) return false;
        OrderItemEntityId that = (OrderItemEntityId) o;
        return id.equals(that.id) && orderEntity.equals(that.orderEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderEntity);
    }
}
