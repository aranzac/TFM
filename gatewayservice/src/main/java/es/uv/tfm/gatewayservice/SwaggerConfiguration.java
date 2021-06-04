package es.uv.tfm.gatewayservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Server;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;

@Primary
@Configuration
//@EnableOpenApi

public class SwaggerConfiguration  {
	
	
//	@Bean
//    public Docket api() {
//        Server serverLocal = new Server("local", "http://localhost:8080", "for local usages", Collections.emptyList(), Collections.emptyList());
//        return new Docket(DocumentationType.OAS_30)
//                .servers(serverLocal)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("es.uv.tfm.gatewayservice"))
//                .paths(PathSelectors.ant("/users/*"))
//                .build();
//    }
//	
//	
//	private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Graph API Documentation")
//                .description("Code first approach")
//                .version("1.0.0")
//                .build();
//    }
	
//	@Autowired
//	RouteDefinitionLocator locator;
//
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
//	
//	@Autowired
//	private RouteLocator routeLocator;
//
//	@Override
//	public List<SwaggerResource> get() {
//		List<SwaggerResource> resources = new ArrayList<>();
//
//		routeLocator.getRoutes().subscribe(route -> {
//			String name = route.getId().split("_")[1];
//			resources.add(swaggerResource(name, "/" + name.toLowerCase() + "/v3/api-docs", "1.0"));
//		});
//
//		return resources;
//	}
//
//	private SwaggerResource swaggerResource(final String name, final String location, final String version) {
//		SwaggerResource swaggerResource = new SwaggerResource();
//		swaggerResource.setName(name);
//		swaggerResource.setLocation(location);
//		swaggerResource.setSwaggerVersion(version);
//		return swaggerResource;
//	}
//	
	
//	 @Bean
//	    public Docket api() { 
//	        return new Docket(DocumentationType.SWAGGER_2)  
//	          .select()                                  
//	          .apis(RequestHandlerSelectors.any())              
//	          .paths(PathSelectors.any())                          
//	          .build();                                           
//	    }
}
