package com.foodie.kafka.producer;

import com.foodie.kafka.producer.exception.KafkaProducerException;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecordBase;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PreDestroy;
import java.io.Serializable;

@Slf4j
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K,V>{

    private final KafkaTemplate<K,V> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message, ListenableFutureCallback<SendResult<K, V>> callback) {
        try {
            ListenableFuture<SendResult<K,V>> kafkaResultFuture= kafkaTemplate.send(topicName,key,message);
            kafkaResultFuture.addCallback(callback);
        }
        catch (KafkaException e){
            log.error("Error on kafka producer with Key {} message {} and exception {}",key,message,e.getMessage());
            throw new KafkaProducerException("Error on Kafka Producer with key "+key+" and message "+message);

        }
    }

    @PreDestroy
    public void close(){
        if(kafkaTemplate!=null){
            log.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }
}
