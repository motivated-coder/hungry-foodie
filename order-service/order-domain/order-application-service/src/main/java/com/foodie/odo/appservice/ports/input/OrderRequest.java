package com.foodie.odo.appservice.ports.input;

import com.foodie.odo.appservice.dto.OrderDto;
import com.foodie.odo.appservice.dto.OrderDtoResponse;
import com.foodie.odo.appservice.dto.TrackOrderResponse;
import com.foodie.odo.appservice.dto.TrackingOrderQuery;

public interface OrderRequest {

    OrderDtoResponse save(OrderDto orderDto);
    TrackOrderResponse trackOrder(TrackingOrderQuery trackingOrderQuery);
}
