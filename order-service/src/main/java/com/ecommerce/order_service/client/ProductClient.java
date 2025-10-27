package com.ecommerce.order_service.client;

import com.ecommerce.order_service.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "${product-service.url}")
public interface ProductClient {
    @GetMapping("/api/v1/products/{sku}")
    ProductResponse getProduct(@PathVariable("sku") String sku);
}
