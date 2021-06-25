package es.uv.tfm.graphicservice.feign;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;

//@FeignClient(name = "userservice", url = "localhost:8081")
//interface UserClient {
//	
//	@GetMapping("/account/")
//	List<User> getAll();
//	
//	
////	@GetMapping("/{company}")
////	List<User> getByCompany(@PathVariable("company") String company);
//}
