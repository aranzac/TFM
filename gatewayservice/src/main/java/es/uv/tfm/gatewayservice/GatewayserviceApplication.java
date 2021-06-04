package es.uv.tfm.gatewayservice;

import java.util.ArrayList;
import java.util.List;

//import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableDiscoveryClient
@SpringBootApplication
@EnableAutoConfiguration
//@EnableSwagger2
public class GatewayserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserviceApplication.class, args);
	}
	
	
	@Autowired
	RouteDefinitionLocator locator;

//	@Bean
//	public List<GroupedOpenApi> apis() {
//		List<GroupedOpenApi> groups = new ArrayList<>();
//		List<RouteDefinition> definitions = locator.getRouteDefinitions().collectList().block();
//		definitions.stream().filter(routeDefinition -> routeDefinition.getId().matches(".*service")).forEach(routeDefinition -> {
//			String name = routeDefinition.getId().replaceAll("service", "");
//			System.out.println("ola " + name);
//			groups.add(GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").setGroup(name).build());
//		});
//		System.out.println("size " + groups.size());
//		return groups;
//	}
	

}
