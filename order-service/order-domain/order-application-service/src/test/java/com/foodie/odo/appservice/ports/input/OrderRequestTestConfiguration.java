package com.foodie.odo.appservice.ports.input;

import com.foodie.odo.appservice.OrderRequestHandler;
import com.foodie.odo.appservice.OrderRequestHelper;
import com.foodie.odo.appservice.mapper.OrderMapper;
import com.foodie.odo.appservice.ports.output.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.foodie.odo.appservice.ports.output.repositories.CustomerRepository;
import com.foodie.odo.appservice.ports.output.repositories.OrderRepository;
import com.foodie.odo.appservice.ports.output.repositories.RestaurantRepository;
import com.foodie.odo.core.OrderDomainService;
import com.foodie.odo.core.OrderDomainServiceImpl;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = {"com.foodie"})
public class OrderRequestTestConfiguration {

    @Bean
    public OrderRequestHelper getOrderRequestHelper(){
        return Mockito.mock(OrderRequestHelper.class);
    }

    @Bean
    public OrderRepository getOrderRepository(){
        return Mockito.mock(OrderRepository.class);
    }

    @Bean
    public OrderDomainService getOrderDomainService(){
        return new OrderDomainServiceImpl();
    }

    @Bean
    public CustomerRepository getCustomerRepository(){
        return Mockito.mock(CustomerRepository.class);
    }

    @Bean
    public RestaurantRepository getRestaurantRepository(){
        return Mockito.mock(RestaurantRepository.class);
    }

    @Bean
    public OrderCreatedPaymentRequestMessagePublisher getOrderCreatedPaymentRequestMessagePublisher(){
        return Mockito.mock(OrderCreatedPaymentRequestMessagePublisher.class);
    }

    @Bean
    public OrderRequestHandler getOrderRequestHandler(){
        return Mockito.mock(OrderRequestHandler.class);
    }

    @Bean
    public OrderMapper getOrderMapper(){
        return Mockito.mock(OrderMapper.class);
    }

//    @Bean
//    public OrderRequest getOrderRequest(){
//        return Mockito.mock(OrderRequest.class);
//    }
}
