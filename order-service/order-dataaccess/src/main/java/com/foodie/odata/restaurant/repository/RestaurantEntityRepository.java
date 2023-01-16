package com.foodie.odata.restaurant.repository;

import com.foodie.odata.restaurant.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantEntityRepository extends JpaRepository<RestaurantEntity, UUID> {
}
