package com.foodie.odo.appservice;

import com.foodie.odo.appservice.dto.TrackOrderResponse;
import com.foodie.odo.appservice.dto.TrackingOrderQuery;
import com.foodie.odo.appservice.exception.RestaurantNotFoundException;
import com.foodie.odo.appservice.mapper.OrderMapper;
import com.foodie.odo.appservice.ports.output.publisher.OrderCreatedPaymentRequestMessagePublisher;
import com.foodie.odo.appservice.ports.output.repositories.OrderRepository;
import com.foodie.odo.appservice.ports.output.repositories.RestaurantRepository;
import com.foodie.odo.core.OrderDomainService;
import com.foodie.odo.core.entity.Customer;
import com.foodie.odo.appservice.exception.CustomerNotFoundException;
import com.foodie.odo.appservice.dto.OrderDto;
import com.foodie.odo.appservice.ports.output.repositories.CustomerRepository;
import com.foodie.odo.core.entity.Order;
import com.foodie.odo.core.entity.Restaurant;
import com.foodie.odo.core.event.OrderCreatedEvent;
import com.foodie.odo.core.exception.OrderDomainException;
import com.foodie.odo.core.exception.OrderNotFoundException;

import java.util.Objects;
import java.util.Optional;

public class OrderRequestHandler {

    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;
    private final OrderDomainService orderDomainService;
    private final OrderMapper orderMapper;
    private  final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    public OrderRequestHandler(CustomerRepository customerRepository, RestaurantRepository restaurantRepository, OrderRepository orderRepository, OrderDomainService orderDomainService, OrderMapper orderMapper, OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderRepository = orderRepository;
        this.orderDomainService = orderDomainService;
        this.orderMapper = orderMapper;
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
    }

    public Order preSaveValidation(OrderDto orderDto){
        validateCustomer(orderDto);
        Restaurant restaurant = validateRestaurant(orderDto);

        Order order= orderMapper.orderDtoToOrder(orderDto);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.preSaveOrderValidationAndInitialization(order,restaurant);
        saveOrder(order);
        //TODO : Add logger that order is saved
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
        return order;
    }

    private void saveOrder(Order order) {
        orderRepository.saveOrder(order);
    }

    private Restaurant validateRestaurant(OrderDto orderDto) {
        Restaurant restaurant = restaurantRepository.getRestaurantById(orderDto.getRestaurantId()).get();
        if(Objects.isNull(restaurant)){
            throw new RestaurantNotFoundException("Restaurant doesn't exist for restaurant id "+orderDto.getRestaurantId());
        }
        else{
            if(!restaurant.isActive()){
                throw new RestaurantNotFoundException("Restaurant is not Active");
            }
        }
        return restaurant;
    }

    private Customer validateCustomer(OrderDto orderDto){
        Customer customer = customerRepository.getCustomerById(orderDto.getCustomerId()).get();
        if(Objects.isNull(customer)){
            throw new CustomerNotFoundException("Customer doesn't exist for id "+orderDto.getCustomerId());
        }
        return customer;
    }

    public TrackOrderResponse track(TrackingOrderQuery trackingOrderQuery) {
        Optional<Order> order = orderRepository.findByTrackingID(trackingOrderQuery.getTrackingId());
        if(order.isEmpty()){
            //TODO:  add logger
            throw new OrderNotFoundException("Order not found for tracking ID "+trackingOrderQuery.getTrackingId());
        }
        return orderMapper.orderToTrackOrderResponse(order);
    }
}















