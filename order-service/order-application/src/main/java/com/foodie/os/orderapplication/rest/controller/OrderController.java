package com.foodie.os.orderapplication.rest.controller;

import com.foodie.odo.appservice.dto.OrderDto;
import com.foodie.odo.appservice.ports.input.OrderRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderRequest orderRequest;

    public OrderController(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto){
        orderRequest.save(orderDto);
        return null;
    }
}
