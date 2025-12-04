package com.infonal.gatewayserver.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder){
        return builder
                .routes()
                .route(
                        "product-service", r-> r
                        .path("/api/v1/products/**")
                        .filters(f->f
                                .retry(config-> config.setRetries(3)))
                        .uri("lb://product-service"))
                .route(
                        "bff-service", r-> r
                                .path("/api/**")
                                .filters(f-> f
                                        .removeRequestHeader("Cookie")
                                        .rewritePath("/api/(?<segment>.*)", "/${segment}")
                                        .tokenRelay())
                                .uri("lb://bff-service")
                )
                .route("fallback", r->r
                        .path("/**")
                        .filters(f->f.setStatus(HttpStatus.NOT_FOUND))
                        .uri("no://no"))
                .build();

    }
}
