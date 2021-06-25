package es.uv.tfm.graphicservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import es.uv.tfm.graphicservice.controller.GraphController;

@SpringBootTest
class GraphicserviceApplicationTests {
	
	@Autowired
	private GraphController graphController;

	@Test
	void contextLoads() {
		assertThat(graphController).isNotNull();
	}

}
