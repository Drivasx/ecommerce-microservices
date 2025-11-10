package com.ecommerce.inventory_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.NotNull
    @Column(name = "product_sku", nullable = false, length = Integer.MAX_VALUE)
    private String productSku;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "last_updated")
    private Instant lastUpdated;

}