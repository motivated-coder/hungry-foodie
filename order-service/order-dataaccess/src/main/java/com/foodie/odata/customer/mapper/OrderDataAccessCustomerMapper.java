package com.foodie.odata.customer.mapper;

import com.foodie.common.valueobject.CustomerId;
import com.foodie.odata.customer.entity.CustomerEntity;
import com.foodie.odo.core.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderDataAccessCustomerMapper {
    public UUID customerIdToId(CustomerId customerId) {
        return customerId.getT();
    }

    public Customer customerEntityToCustomer(CustomerEntity customerEntity){
        return Customer.builder()
                .id(new CustomerId(customerEntity.getId()))
                .build();
    }
}
