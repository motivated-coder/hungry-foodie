package com.foodie.odo.appservice.ports.input;

import com.foodie.odo.appservice.OrderRequestHelper;
import com.foodie.odo.appservice.dto.OrderDto;
import com.foodie.odo.appservice.dto.OrderDtoResponse;
import com.foodie.odo.appservice.dto.TrackOrderResponse;
import com.foodie.odo.appservice.dto.TrackingOrderQuery;
import org.springframework.stereotype.Service;

@Service
public class OrderRequestImpService implements OrderRequest{

    OrderRequestHelper orderRequestHelper;

    public OrderRequestImpService(OrderRequestHelper orderRequestHelper) {
        this.orderRequestHelper = orderRequestHelper;
    }

    @Override
    public OrderDtoResponse save(OrderDto orderDto) {
        return orderRequestHelper.persist(orderDto);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackingOrderQuery trackingOrderQuery) {
        return null;
    }
}
