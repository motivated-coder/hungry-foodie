package com.foodie.odata.customer.adapter;

import com.foodie.common.valueobject.CustomerId;
import com.foodie.odata.customer.entity.CustomerEntity;
import com.foodie.odata.customer.mapper.OrderDataAccessCustomerMapper;
import com.foodie.odata.customer.repository.CustomerEntityRepository;
import com.foodie.odo.appservice.ports.output.repositories.CustomerRepository;
import com.foodie.odo.core.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerEntityRepository customerEntityRepository;
    private final OrderDataAccessCustomerMapper orderDataAccessCustomerMapper;

    public CustomerRepositoryImpl(CustomerEntityRepository customerEntityRepository, OrderDataAccessCustomerMapper orderDataAccessCustomerMapper) {
        this.customerEntityRepository = customerEntityRepository;
        this.orderDataAccessCustomerMapper = orderDataAccessCustomerMapper;
    }

    @Override
    public Optional<Customer> getCustomerById(CustomerId customerId) {
      Optional<CustomerEntity> customerEntity = customerEntityRepository.findById(orderDataAccessCustomerMapper.customerIdToId(customerId));
      return customerEntity.map(orderDataAccessCustomerMapper::customerEntityToCustomer);
    }
}
