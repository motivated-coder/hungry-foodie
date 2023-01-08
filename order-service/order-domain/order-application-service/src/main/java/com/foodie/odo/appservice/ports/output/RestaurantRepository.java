package com.foodie.odo.appservice.ports.output;

import com.foodie.common.valueobject.RestaurantId;
import com.foodie.odo.core.entity.Restaurant;

import java.util.Optional;
import java.util.UUID;

public interface RestaurantRepository {

    Optional<Restaurant> getRestaurantById(UUID restaurantId);
}
