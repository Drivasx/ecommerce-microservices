package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.entity.Product;
import com.ecommerce.product_service.service.ProductService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    @GetMapping("/{sku}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable("sku") String sku){
        return productService.getProductBySku(sku);
    }
}
