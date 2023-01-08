package com.foodie.odo.appservice.ports.output;

import com.foodie.odo.core.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> getCustomerById(UUID customerId);
}
