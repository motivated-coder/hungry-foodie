package com.foodie.odo.appservice.ports.input.listener.restaurant;

import com.foodie.odo.appservice.dto.RestaurantResponse;

public interface RestaurantResponseListener {
    void orderApproved(RestaurantResponse restaurantResponse);
    void orderRejected(RestaurantResponse restaurantResponse);
}
