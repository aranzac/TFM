package es.uv.tfm.pruebaservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/prueba")
public class PruebaController {

	@GetMapping("/message")
	public String test() {
		return "Hello JavaInUse Called in First Service";
	}
	
	  @GetMapping(value = "/products")
	  public List<String> getAllproduct(){
	    return Arrays.asList("Eureka", "Hystrix","Zuul",  "Ribbon");
	    			  }
	
}
