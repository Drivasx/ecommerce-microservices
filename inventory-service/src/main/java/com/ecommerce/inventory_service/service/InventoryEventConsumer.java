package com.ecommerce.inventory_service.service;

import com.ecommerce.common.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class InventoryEventConsumer {

    private final InventoryService inventoryService;

    public InventoryEventConsumer(InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @KafkaListener(topics = "order.created", groupId = "inventory-group")
    public void handleOrderCreated(OrderCreatedEvent event){
        log.info("Getting order: {}", event.getOrderId());
        for (Map.Entry<String, Integer> entry:event.getSkusQuantity().entrySet()){
            String sku = entry.getKey();
            Integer quantity = entry.getValue();

            log.info("Reducing stock: {} {}", sku, quantity);

            inventoryService.reduceStock(sku, quantity);
        }
        log.info("Stock updated: {}", event.getOrderId());
    }
}
