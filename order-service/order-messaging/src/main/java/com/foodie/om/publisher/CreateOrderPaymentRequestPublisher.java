package com.foodie.om.publisher;

import com.foodie.kafka.order.avro.model.PaymentRequestAvroModel;
import com.foodie.kafka.producer.KafkaProducer;
import com.foodie.odo.appservice.config.OrderApplicationServiceConfig;
import com.foodie.odo.appservice.ports.output.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.foodie.odo.core.event.OrderCreatedEvent;
import com.foodie.om.mapper.OrderMessagingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateOrderPaymentRequestPublisher implements OrderCreatedPaymentRequestMessagePublisher {
    private final OrderMessagingMapper orderMessagingMapper;
    private final KafkaProducer<String, PaymentRequestAvroModel> kafkaProducer;
    private final OrderApplicationServiceConfig orderApplicationServiceConfig;
    private final OrderKafkaHelper orderKafkaHelper;

    public CreateOrderPaymentRequestPublisher(OrderMessagingMapper orderMessagingMapper, KafkaProducer kafkaProducer, OrderApplicationServiceConfig orderApplicationServiceConfig, OrderKafkaHelper orderKafkaHelper) {
        this.orderMessagingMapper = orderMessagingMapper;
        this.kafkaProducer = kafkaProducer;
        this.orderApplicationServiceConfig = orderApplicationServiceConfig;
        this.orderKafkaHelper = orderKafkaHelper;
    }

    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        String orderId = domainEvent.getOrder().getId().getT().toString();
        log.info("Received OrderCreatedEvent for order id: {}", orderId);
        PaymentRequestAvroModel paymentRequestAvroModel = orderMessagingMapper
                .orderCreatedEventToPaymentRequestAvroModel(domainEvent);
        try {
            kafkaProducer.send(orderApplicationServiceConfig.getPaymentRequestTopicName(), orderId,
                    paymentRequestAvroModel
                    , orderKafkaHelper.getCallBack(orderApplicationServiceConfig.getPaymentResponseTopicName(),
                            paymentRequestAvroModel, orderId, "PaymentRequestAvroModel"));
            log.info("PaymentRequestAvroModel successfully sent to kafka for order id {}", paymentRequestAvroModel.getOrderId());
        }
        catch (Exception e){
            log.error("Error while sending PaymentRequestAvroModel message to kafka " +
                    "with order id {} , error {}",paymentRequestAvroModel.getOrderId(), e.getMessage());
        }

    }


}
