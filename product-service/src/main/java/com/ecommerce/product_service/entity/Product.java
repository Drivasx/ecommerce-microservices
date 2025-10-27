package com.ecommerce.product_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
@Data
public class Product {

    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private Integer stock;
    @Indexed(unique = true)
    private String sku;
    private Boolean active;
}
