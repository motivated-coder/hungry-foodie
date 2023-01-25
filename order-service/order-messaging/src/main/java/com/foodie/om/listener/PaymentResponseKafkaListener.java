package com.foodie.om.listener;

import com.foodie.kafka.consumer.KafkaConsumer;
import com.foodie.kafka.order.avro.model.PaymentResponseAvroModel;
import com.foodie.kafka.order.avro.model.PaymentStatus;
import com.foodie.odo.appservice.ports.input.listener.payment.PaymentResponseListener;
import com.foodie.om.mapper.OrderMessagingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.List;

@Slf4j
public class PaymentResponseKafkaListener implements KafkaConsumer<PaymentResponseAvroModel> {

    private final OrderMessagingMapper orderMessagingMapper;
    private final PaymentResponseListener paymentResponseListener;

    public PaymentResponseKafkaListener(OrderMessagingMapper orderMessagingMapper, PaymentResponseListener paymentResponseListener) {
        this.orderMessagingMapper = orderMessagingMapper;
        this.paymentResponseListener = paymentResponseListener;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.payment-consumer-group-id}", topics = "${order-service.payment-response-topic-name}")
    public void receive(@Payload List<PaymentResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(paymentResponseAvroModel -> {
            if (PaymentStatus.COMPLETED == paymentResponseAvroModel.getPaymentStatus()) {
                log.info("Processing successful payment for order id: {}", paymentResponseAvroModel.getOrderId());
                paymentResponseListener.paymentCompleted(orderMessagingMapper
                        .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
            } else if (PaymentStatus.CANCELLED == paymentResponseAvroModel.getPaymentStatus() ||
                    PaymentStatus.FAILED == paymentResponseAvroModel.getPaymentStatus()) {
                log.info("Processing unsuccessful payment for order id: {} with failure messages: {}", paymentResponseAvroModel.getOrderId(),
                        String.join(",",paymentResponseAvroModel.getFailureMessages()));
                paymentResponseListener.paymentCancelled(orderMessagingMapper
                        .paymentResponseAvroModelToPaymentResponse(paymentResponseAvroModel));
            }
        });
    }
}
