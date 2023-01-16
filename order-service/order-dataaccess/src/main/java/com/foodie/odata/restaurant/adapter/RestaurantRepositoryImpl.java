package com.foodie.odata.restaurant.adapter;

import com.foodie.odata.restaurant.entity.RestaurantEntity;
import com.foodie.odata.restaurant.mapper.OrderDataAccessRestaurantMapper;
import com.foodie.odata.restaurant.repository.RestaurantEntityRepository;
import com.foodie.odo.appservice.ports.output.repositories.RestaurantRepository;
import com.foodie.odo.core.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private final RestaurantEntityRepository restaurantEntityRepository;
    private final OrderDataAccessRestaurantMapper orderDataAccessRestaurantMapper;

    public RestaurantRepositoryImpl(RestaurantEntityRepository restaurantEntityRepository, OrderDataAccessRestaurantMapper orderDataAccessRestaurantMapper) {
        this.restaurantEntityRepository = restaurantEntityRepository;
        this.orderDataAccessRestaurantMapper = orderDataAccessRestaurantMapper;
    }

    @Override
    public Optional<Restaurant> getRestaurantById(UUID restaurantId) {
        RestaurantEntity restaurantEntity = restaurantEntityRepository.findById(restaurantId).get();
        return Optional.of(orderDataAccessRestaurantMapper.restaurantEntityToRestaurant(restaurantEntity));
    }
}
