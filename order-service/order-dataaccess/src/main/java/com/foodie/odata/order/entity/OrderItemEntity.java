package com.foodie.odata.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@IdClass(value = OrderItemEntityId.class)
@Table(name ="order_items")
public class OrderItemEntity {
    @Id
    private Long id;
    @Id
    @ManyToOne()
    @JoinColumn(name="order_entity_id")
    private OrderEntity orderEntity;

    private UUID productId;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItemEntity)) return false;
        OrderItemEntity that = (OrderItemEntity) o;
        return id.equals(that.id) && orderEntity.equals(that.orderEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderEntity);
    }
}























