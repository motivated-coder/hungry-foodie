package com.foodie.odo.appservice;

import com.foodie.odo.appservice.dto.OrderDto;
import com.foodie.odo.appservice.dto.OrderDtoResponse;
import com.foodie.odo.appservice.mapper.OrderMapper;
import com.foodie.odo.core.OrderDomainService;
import com.foodie.odo.core.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderRequestHelper {

    private final OrderMapper orderMapper;
    private final OrderDomainService orderDomainService;
    private final OrderRequestHandler orderRequestHandler;

    public OrderRequestHelper(OrderMapper orderMapper, OrderDomainService orderDomainService, OrderRequestHandler orderRequestHandler) {
        this.orderMapper = orderMapper;
        this.orderDomainService = orderDomainService;
        this.orderRequestHandler = orderRequestHandler;
    }

    public OrderDtoResponse persist(OrderDto orderDto) {
        orderRequestHandler.preSaveValidation(orderDto);
        Order order = orderMapper.orderDtoToOrder(orderDto);
        return null;
    }
}
