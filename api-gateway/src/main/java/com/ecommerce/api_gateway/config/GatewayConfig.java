package com.ecommerce.api_gateway.config;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.*;

import java.net.URI;

@Configuration
public class GatewayConfig {

    @Bean
    public RouterFunction<ServerResponse> gatewayProductRoutes(){
        return GatewayRouterFunctions.route("product-service")
                .route(RequestPredicates.path("/api/v1/products/**"), HandlerFunctions.http("http://localhost:8081"))
                //.filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewayOrderRoutes(){
        return GatewayRouterFunctions.route("order-service")
                .route(RequestPredicates.path("/api/v1/orders/**"), HandlerFunctions.http("http://localhost:8082"))
                //.filter(CircuitBreakerFilterFunctions.circuitBreaker("orderServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> gatewayInventoryRoutes(){
        return GatewayRouterFunctions.route("inventory-service")
                .route(RequestPredicates.path("/api/v1/inventory/**"), HandlerFunctions.http("http://localhost:8083"))
                //.filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }
}
