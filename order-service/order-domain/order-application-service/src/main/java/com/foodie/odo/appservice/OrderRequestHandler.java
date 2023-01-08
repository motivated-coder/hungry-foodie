package com.foodie.odo.appservice;

import com.foodie.odo.appservice.exception.RestaurantNotFoundException;
import com.foodie.odo.appservice.mapper.OrderMapper;
import com.foodie.odo.appservice.ports.output.RestaurantRepository;
import com.foodie.odo.core.OrderDomainService;
import com.foodie.odo.core.entity.Customer;
import com.foodie.odo.appservice.exception.CustomerNotFoundException;
import com.foodie.odo.appservice.dto.OrderDto;
import com.foodie.odo.appservice.ports.output.CustomerRepository;
import com.foodie.odo.core.entity.Order;
import com.foodie.odo.core.entity.Restaurant;

import java.util.Objects;

public class OrderRequestHandler {

    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDomainService orderDomainService;
    private final OrderMapper orderMapper;

    public OrderRequestHandler(CustomerRepository customerRepository, RestaurantRepository restaurantRepository, OrderDomainService orderDomainService, OrderMapper orderMapper) {
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDomainService = orderDomainService;
        this.orderMapper = orderMapper;
    }

    public void preSaveValidation(OrderDto orderDto){
        Customer customer = validateCustomer(orderDto);
        Restaurant restaurant = validateRestaurant(orderDto);

        Order order= orderMapper.orderDtoToOrder(orderDto);
        orderDomainService.preSaveOrderValidation(order,restaurant);

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
}















