package com.foodie.os.orderapplication.rest.controller;

import com.foodie.odo.appservice.dto.OrderDto;
import com.foodie.odo.appservice.dto.OrderDtoResponse;
import com.foodie.odo.appservice.dto.TrackOrderResponse;
import com.foodie.odo.appservice.dto.TrackingOrderQuery;
import com.foodie.odo.appservice.ports.input.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderRequest orderRequest;

    public OrderController(OrderRequest orderRequest) {
        this.orderRequest = orderRequest;
    }

    @PostMapping("/create")
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto){
        OrderDtoResponse orderDtoResponse = orderRequest.save(orderDto);
        return new ResponseEntity<>(orderDtoResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{trackingId}")
    public ResponseEntity trackOrder(@PathVariable UUID trackingId){
        TrackOrderResponse trackOrderResponse = orderRequest.trackOrder(new TrackingOrderQuery(trackingId));
        return new ResponseEntity<>(trackOrderResponse, HttpStatus.OK);
    }
}
