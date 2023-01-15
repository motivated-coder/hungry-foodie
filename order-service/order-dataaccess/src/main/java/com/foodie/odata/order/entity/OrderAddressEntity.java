package com.foodie.odata.order.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderAddressEntity {
    @Id
    private UUID id;
    @OneToOne
    @JoinColumn(name = "order_entity_id")
    private OrderEntity orderEntity;

    private  String street;
    private  String postalCode;
    private  String city;
}
