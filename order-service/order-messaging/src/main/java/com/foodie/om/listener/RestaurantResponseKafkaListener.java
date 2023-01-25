package com.foodie.om.listener;

import com.foodie.common.enums.OrderApprovalStatus;
import com.foodie.kafka.consumer.KafkaConsumer;
import com.foodie.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.foodie.odo.appservice.ports.input.listener.restaurant.RestaurantResponseListener;
import com.foodie.om.mapper.OrderMessagingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RestaurantResponseKafkaListener implements KafkaConsumer<RestaurantApprovalResponseAvroModel> {

    private final OrderMessagingMapper orderMessagingMapper;
    private final RestaurantResponseListener restaurantResponseListener;

    public RestaurantResponseKafkaListener(OrderMessagingMapper orderMessagingMapper, RestaurantResponseListener restaurantResponseListener) {
        this.orderMessagingMapper = orderMessagingMapper;
        this.restaurantResponseListener = restaurantResponseListener;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.restaurant-approval-consumer-group-id}",
            topics = "${order-service.restaurant-approval-response-topic-name}")
    public void receive(@Payload List<RestaurantApprovalResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of restaurant approval responses received with keys {}, partitions {} and offsets {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(restaurantApprovalResponseAvroModel -> {
//            OrderApprovalStatus.APPROVED == restaurantApprovalResponseAvroModel.getOrderApprovalStatus()
//            OrderApprovalStatus.REJECTED == restaurantApprovalResponseAvroModel.getOrderApprovalStatus()
            //TODO: Check if it works
            if (OrderApprovalStatus.APPROVED.equals(restaurantApprovalResponseAvroModel.getOrderApprovalStatus())) {
                log.info("Processing approved order for order id: {}",
                        restaurantApprovalResponseAvroModel.getOrderId());
                restaurantResponseListener.orderApproved(orderMessagingMapper
                        .approvalResponseAvroModelToApprovalResponse(restaurantApprovalResponseAvroModel));
            } else if (OrderApprovalStatus.REJECTED.equals(restaurantApprovalResponseAvroModel.getOrderApprovalStatus())) {
                log.info("Processing rejected order for order id: {}, with failure messages: {}",
                        restaurantApprovalResponseAvroModel.getOrderId(),
                        String.join(",",
                                restaurantApprovalResponseAvroModel.getFailureMessages()));
                restaurantResponseListener.orderRejected(orderMessagingMapper
                        .approvalResponseAvroModelToApprovalResponse(restaurantApprovalResponseAvroModel));
            }
        });
}
}
