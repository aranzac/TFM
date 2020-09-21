package es.uv.tfm.hystrixdashboard;

import java.net.URI;
import java.util.List;

import org.springframework.boot.SpringApplication;


@SpringBootApplication
public class HystrixdashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixdashboardApplication.class, args);
	}

//	@GetMapping
//	  public List<String> cloudProductList() {
//	RestTemplate restTemplate = new RestTemplate();
//	    URI uri = URI.create("http://localhost:8090/products");
//	    return restTemplate.getForObject(uri, List.class);
//	  }
//
//	
}
