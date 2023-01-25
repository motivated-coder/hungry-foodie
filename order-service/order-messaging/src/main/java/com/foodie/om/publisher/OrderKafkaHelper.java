package com.foodie.om.publisher;

import com.foodie.kafka.order.avro.model.PaymentRequestAvroModel;
import com.foodie.kafka.order.avro.model.RestaurantApprovalRequestAvroModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class OrderKafkaHelper {

    //TODO: check functionality how generic type is used in return type
    public <T> ListenableFutureCallback<SendResult<String, T>> getCallBack(String topicName,
                                                                                             T avroModel,
                                                                                             String orderId, String avroModelName) {
        return new ListenableFutureCallback<SendResult<String, T>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending {} message {} to topic {} ",avroModelName,
                        avroModel.toString(), topicName, ex);
            }

            @Override
            public void onSuccess(SendResult<String, T> result) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Received successful response from Kafka for order id: {}" +
                                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        orderId,
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp());

            }
        };
    }


//    public ListenableFutureCallback<SendResult<String, RestaurantApprovalRequestAvroModel>> getCallBack(String paymentResponseTopicName,
//                                                                                             RestaurantApprovalRequestAvroModel restaurantApprovalRequestAvroModel,
//                                                                                             String orderId, String restaurantRequestAvroModel1) {
//        return new ListenableFutureCallback<SendResult<String, RestaurantApprovalRequestAvroModel>>() {
//            @Override
//            public void onFailure(Throwable ex) {
//                log.error("Error while sending PaymentRequestAvroModel message {} to topic {} ",
//                        restaurantApprovalRequestAvroModel.toString(), paymentResponseTopicName, ex);
//            }
//
//            @Override
//            public void onSuccess(SendResult<String, PaymentRequestAvroModel> result) {
//                RecordMetadata metadata = result.getRecordMetadata();
//                log.info("Received successful response from Kafka for order id: {}" +
//                                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
//                        orderId,
//                        metadata.topic(),
//                        metadata.partition(),
//                        metadata.offset(),
//                        metadata.timestamp());
//
//            }
//        };
//    }
}
