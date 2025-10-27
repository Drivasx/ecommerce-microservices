package com.ecommerce.order_service.service;

import com.ecommerce.order_service.client.ProductClient;
import com.ecommerce.order_service.dto.OrderItemRequest;
import com.ecommerce.order_service.dto.OrderRequest;
import com.ecommerce.order_service.dto.ProductResponse;
import com.ecommerce.order_service.entity.Order;
import com.ecommerce.order_service.entity.OrderItem;
import com.ecommerce.order_service.exception.ResourceNotFoundException;
import com.ecommerce.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;

    public Order createOrder(OrderRequest orderRequest){
        log.info("Creating new order for client: {}", orderRequest.getCustomerId());

        Order order = new Order();
        order.setCustomerId(orderRequest.getCustomerId());
        order.setStatus("PENDING");
        order.setCreatedAt(Instant.now());
        order.setUpdatedAt(Instant.now());
        order.setTotal(BigDecimal.ZERO);

        order = orderRepository.save(order);

        BigDecimal total = BigDecimal.ZERO;

        for(OrderItemRequest itemRequest: orderRequest.getItems()){
            try {
                ProductResponse product = productClient.getProduct(itemRequest.getSku());
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(order);
                orderItem.setProductId(product.getId());
                orderItem.setQuantity(itemRequest.getQuantity());
                orderItem.setPrice(product.getPrice());

                total = total.add(product.getPrice()).multiply(BigDecimal.valueOf(itemRequest.getQuantity()));
            } catch (RuntimeException e) {
                log.error("Error processing the product: {}", itemRequest.getSku(), e);
                throw new RuntimeException(e);
            }
        }
        order.setTotal(total);
        return orderRepository.save(order);
    }

    public Order getOrder(Integer id){
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order Not Found"));
    }

    public List<Order> getOrdersByCustomerId(String customerId){
        return orderRepository.findByCustomerId(customerId);
    }

    public void updateOrderStatus(Integer orderId, String status){
        Order order = getOrder(orderId);

        order.setStatus(status);
        order.setUpdatedAt(Instant.now());
        orderRepository.save(order);
        log.info("Order status updated: {}", orderId, status);
    }
}
