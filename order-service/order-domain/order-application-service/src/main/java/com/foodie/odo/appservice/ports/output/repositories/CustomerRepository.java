package com.foodie.odo.appservice.ports.output.repositories;

import com.foodie.common.valueobject.CustomerId;
import com.foodie.odo.core.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> getCustomerById(CustomerId customerId);
}
