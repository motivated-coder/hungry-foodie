package com.foodie.odata.order.adapter;

import com.foodie.odata.order.mapper.OrderDataAccessMapper;
import com.foodie.odata.order.repository.OrderEntityRepository;
import com.foodie.odo.appservice.ports.output.repositories.OrderRepository;
import com.foodie.odo.core.entity.Order;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderEntityRepository orderEntityRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    public OrderRepositoryImpl(OrderEntityRepository orderEntityRepository, OrderDataAccessMapper orderDataAccessMapper) {
        this.orderEntityRepository = orderEntityRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Override
    public Order saveOrder(Order order) {
        return orderDataAccessMapper.orderEntityToOrder(orderEntityRepository.save(orderDataAccessMapper.orderToOrderEntity(order)));
    }

    @Override
    public Optional<Order> findByTrackingID(UUID trackingId) {
        return Optional.of(orderDataAccessMapper.orderEntityToOrder(orderEntityRepository.findByTrackingId(trackingId).get())) ;
    }
}
