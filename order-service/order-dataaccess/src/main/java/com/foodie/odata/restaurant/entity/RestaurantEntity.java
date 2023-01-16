package com.foodie.odata.restaurant.entity;

import com.foodie.odata.order.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "restaurants")
public class RestaurantEntity {
    @Id
    private UUID id;
    private Boolean active;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "restaurant_products"
            , joinColumns = @JoinColumn(name="restaurant_id")
            , inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> products;
}
