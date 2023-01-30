package com.foodie.odo.appservice.ports.input;

import com.foodie.common.entity.Product;
import com.foodie.common.enums.OrderStatus;
import com.foodie.common.valueobject.CustomerId;
import com.foodie.common.valueobject.Money;
import com.foodie.common.valueobject.ProductId;
import com.foodie.common.valueobject.RestaurantId;
import com.foodie.odo.appservice.OrderRequestHandler;
import com.foodie.odo.appservice.OrderRequestHelper;
import com.foodie.odo.appservice.dto.Item;
import com.foodie.odo.appservice.dto.OrderAddress;
import com.foodie.odo.appservice.dto.OrderDto;
import com.foodie.odo.appservice.dto.OrderDtoResponse;
import com.foodie.odo.appservice.mapper.OrderMapper;
import com.foodie.odo.appservice.ports.output.repositories.CustomerRepository;
import com.foodie.odo.appservice.ports.output.repositories.OrderRepository;
import com.foodie.odo.appservice.ports.output.repositories.RestaurantRepository;
import com.foodie.odo.core.OrderDomainService;
import com.foodie.odo.core.entity.Customer;
import com.foodie.odo.core.entity.Restaurant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OrderRequestTestConfiguration.class)
class OrderRequestServiceTest {

    OrderDto orderDto;

    @Autowired
    OrderRequest orderRequestService;

    @Autowired
    OrderRequestHelper orderRequestHelper;

    @Autowired
    OrderRequestHandler orderRequestHandler;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDomainService orderDomainService;

    private final UUID CUSTOMER_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final UUID PRODUCT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48");
    private final UUID RESTAURANT_ID = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45");
    private final BigDecimal PRICE = new BigDecimal("200.00");
    @BeforeAll
    public void beforeAll() {
        orderDto = OrderDto.builder()
                .address(new OrderAddress("Street 1","834005","Ranchi"))
                .customerId(CUSTOMER_ID)
                .restaurantId(RESTAURANT_ID)
                .items(List.of(Item.builder()
                                .price(new BigDecimal("50.00"))
                                .productId(PRODUCT_ID)
                                .quantity(1)
                                .subtotal(new BigDecimal("50.00"))
                        .build(),
                        Item.builder()
                                .price(new BigDecimal("50.00"))
                                .productId(PRODUCT_ID)
                                .quantity(3)
                                .subtotal(new BigDecimal("150.00"))
                                .build()))
                .price(PRICE)
                .build();

        Customer customer = new Customer();
        customer.setId(new CustomerId(CUSTOMER_ID));

        Restaurant restaurant= Restaurant.builder()
                .id(new RestaurantId(RESTAURANT_ID))
                .active(true)
                .products(List.of(Product.builder()
                                .price(new Money(new BigDecimal("50.00")))
                                .id(new ProductId(PRODUCT_ID))
                                .name("product-1")
                        .build(),
                        Product.builder()
                                .price(new Money(new BigDecimal("50.00")))
                                .id(new ProductId(PRODUCT_ID))
                                .name("product-2")
                                .build()
                        ))
                .build();

        when(customerRepository.getCustomerById(any(CustomerId.class))).thenReturn(Optional.of(customer));
        when(restaurantRepository.getRestaurantById(RESTAURANT_ID)).thenReturn(Optional.of(restaurant));
    }

    @Test
    void save() {
        OrderDtoResponse orderDtoResponse = orderRequestService.save(orderDto);
        assertEquals(OrderStatus.PENDING, orderDtoResponse.getOrderStatus());
    }

    @Test
    void trackOrder() {
    }
}