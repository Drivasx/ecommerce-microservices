package com.ecommerce.order_service.controller;

import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest){
        log.info("Request", orderRequest);
        return new ResponseEntity<>(orderService.createOrder(orderRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable String customerId){
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.getOrder(id));
    }
}
