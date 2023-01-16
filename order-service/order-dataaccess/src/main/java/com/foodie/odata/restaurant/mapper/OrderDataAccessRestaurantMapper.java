package com.foodie.odata.restaurant.mapper;

import com.foodie.common.entity.Product;
import com.foodie.common.valueobject.Money;
import com.foodie.common.valueobject.ProductId;
import com.foodie.common.valueobject.RestaurantId;
import com.foodie.odata.order.entity.ProductEntity;
import com.foodie.odata.restaurant.entity.RestaurantEntity;
import com.foodie.odo.core.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderDataAccessRestaurantMapper {
    public Restaurant restaurantEntityToRestaurant(RestaurantEntity restaurantEntity) {
        return Restaurant.builder()
                .id(new RestaurantId(restaurantEntity.getId()))
                .active(restaurantEntity.getActive())
                .products(prductEntitiesToProducts(restaurantEntity.getProducts()))
                .build();
    }

    private List<Product> prductEntitiesToProducts(List<ProductEntity> products) {
        return products.stream().map(productEntity -> Product.builder()
                .name(productEntity.getName())
                .id(new ProductId(productEntity.getId()))
                .price(new Money(productEntity.getPrice()))
                .build()).collect(Collectors.toList());
    }
}
