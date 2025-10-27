package com.ecommerce.order_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "product_id", length = Integer.MAX_VALUE)
    private String productId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;

}