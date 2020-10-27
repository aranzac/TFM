package es.uv.tfm.gatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/account/**")
                        .uri("http://localhost:8081/")
                        .id("userService"))
                .route(r -> r.path("/roles/**").uri("http://localhost:8081/"))
                .route(r -> r.path("/hello/**").uri("http://localhost:8081/"))

                .build()
                ;
    }

}