package es.uv.tfm.userservice.test;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import es.uv.tfm.userservice.controller.RoleController;

@SpringBootTest
@AutoConfigureMockMvc
class UserserviceApplicationTests {
	
	@Autowired
	private RoleController roleController;

	@Test
	void contextLoads() {
		assertThat(roleController).isNotNull();
	}

}
