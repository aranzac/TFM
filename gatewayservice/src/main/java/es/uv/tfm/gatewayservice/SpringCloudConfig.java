package es.uv.tfm.gatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

	@Bean
	public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/account/**").uri("http://localhost:8081/"))
				.route(r -> r.path("/auth/**").uri("http://localhost:8081/"))
				.route(r -> r.path("/account/user/**").uri("http://localhost:8081/"))
				.route(r -> r.path("/roles/**").uri("http://localhost:8081/"))

				.build();
	}

}