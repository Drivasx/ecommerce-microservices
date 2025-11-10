package com.ecommerce.order_service.service;

import com.ecommerce.common.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, Object> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderCreatedEvent(OrderCreatedEvent orderCreatedEvent){
        kafkaTemplate.send("order.created", orderCreatedEvent);
        log.info("Event created: {}", orderCreatedEvent);
    }
}
