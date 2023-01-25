package com.foodie.om.publisher;

import com.foodie.kafka.order.avro.model.PaymentRequestAvroModel;
import com.foodie.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import com.foodie.kafka.producer.KafkaProducer;
import com.foodie.odo.appservice.config.OrderApplicationServiceConfig;
import com.foodie.odo.appservice.ports.output.publisher.restaurant.OrderPaidRestaurantRequestPublisher;
import com.foodie.odo.core.event.OrderPaidEvent;
import com.foodie.om.mapper.OrderMessagingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OrderPaidRestaurantRequestMessagePublisher implements OrderPaidRestaurantRequestPublisher {
    private final OrderMessagingMapper orderMessagingMapper;
    private final KafkaProducer<String, RestaurantApprovalRequestAvroModel> kafkaProducer;
    private final OrderApplicationServiceConfig orderApplicationServiceConfig;
    private final OrderKafkaHelper orderKafkaHelper;

    public OrderPaidRestaurantRequestMessagePublisher(OrderMessagingMapper orderMessagingMapper,
                                                      KafkaProducer kafkaProducer,
                                                      OrderApplicationServiceConfig orderApplicationServiceConfig,
                                                      OrderKafkaHelper orderKafkaHelper) {
        this.orderMessagingMapper = orderMessagingMapper;
        this.kafkaProducer = kafkaProducer;
        this.orderApplicationServiceConfig = orderApplicationServiceConfig;
        this.orderKafkaHelper = orderKafkaHelper;
    }


    @Override
    public void publish(OrderPaidEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getT().toString();
        log.info("Received OrderPaidEvent for order id: {}", orderId);
        RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel = orderMessagingMapper
                .orderPaidEventToRestaurantApprovalRequestAvroModel(domainEvent);
        try {
            kafkaProducer.send(orderApplicationServiceConfig.getRestaurantApprovalRequestTopicName(), orderId,
                    restaurantApprovalRequestAvroModel
                    , orderKafkaHelper.getCallBack(orderApplicationServiceConfig.getRestaurantApprovalRequestTopicName(),
                            restaurantApprovalRequestAvroModel, orderId, "RestaurantApprovalRequest"));
            log.info("RestaurantApprovalRequest successfully sent to kafka for order id {}", restaurantApprovalRequestAvroModel.getOrderId());
        }
        catch (Exception e){
            log.error("Error while sending RestaurantApprovalRequest message to kafka " +
                    "with order id {} , error {}",restaurantApprovalRequestAvroModel.getOrderId(), e.getMessage());
        }
    }
}
