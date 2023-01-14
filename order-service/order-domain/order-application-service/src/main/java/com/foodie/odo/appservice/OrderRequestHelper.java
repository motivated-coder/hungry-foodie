package com.foodie.odo.appservice;

import com.foodie.odo.appservice.dto.OrderDto;
import com.foodie.odo.appservice.dto.OrderDtoResponse;
import com.foodie.odo.appservice.dto.TrackOrderResponse;
import com.foodie.odo.appservice.dto.TrackingOrderQuery;
import com.foodie.odo.appservice.mapper.OrderMapper;
import com.foodie.odo.core.OrderDomainService;
import com.foodie.odo.core.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderRequestHelper {

    private final OrderMapper orderMapper;
    private final OrderRequestHandler orderRequestHandler;

    public OrderRequestHelper(OrderMapper orderMapper, OrderDomainService orderDomainService, OrderRequestHandler orderRequestHandler) {
        this.orderMapper = orderMapper;
        this.orderRequestHandler = orderRequestHandler;
    }

    public OrderDtoResponse persist(OrderDto orderDto) {
        Order order = orderRequestHandler.preSaveValidation(orderDto);
        return orderMapper.orderToOrderDtoResponse(order);
    }

    public TrackOrderResponse track(TrackingOrderQuery trackingOrderQuery) {
        return orderRequestHandler.track(trackingOrderQuery);
    }
}
