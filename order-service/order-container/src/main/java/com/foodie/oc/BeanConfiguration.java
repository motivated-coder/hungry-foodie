package com.foodie.oc;

import com.foodie.odo.core.OrderDomainService;
import com.foodie.odo.core.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public OrderDomainService getOrderDomainService(){
        return new OrderDomainServiceImpl();
    }
}
