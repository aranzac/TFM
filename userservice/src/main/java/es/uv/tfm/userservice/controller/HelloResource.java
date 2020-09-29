package es.uv.tfm.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloResource {

	@GetMapping("/all")
	public String hello() {
		return "Hello Aran";
	}
}
