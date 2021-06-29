package es.uv.tfm.graphicservice.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import es.uv.tfm.graphicservice.model.Graph;
import es.uv.tfm.graphicservice.repository.GraphRepository;
import es.uv.tfm.graphicservice.service.GraphService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class GraphServiceTest {
	
	@Autowired
	private GraphService graphService;
	
	@MockBean
	private GraphRepository graphRepository;
	
	@Test
	public <T> void getGraphsTest() {
		List<T> data = new ArrayList<T>();
		List<T> attributes = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		Graph graph = new Graph("1", "bar", 300, 300, data, color, "test user", "the test graph", attributes);
		Graph graph2 = new Graph("2", "pie", 300, 300, data, color, "test user", "the test graph 2", attributes);

	    List<Graph> allGraphs = new ArrayList<Graph>();
	    allGraphs.add(graph);
	    allGraphs.add(graph2);

		when(graphRepository.findAll()).thenReturn(allGraphs);
		assertEquals(2, graphService.getGraphs().size());
	}
	
	@Test
	public <T> void saveGraphTest() {
		List<T> data = new ArrayList<T>();
		List<T> attributes = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		Graph graph = new Graph("1", "bar", 300, 300, data, color, "test user", "the test graph", attributes);

		when(graphRepository.save(graph)).thenReturn(graph); 

		assertEquals(graph, graphService.saveGraph(graph));
	}
	
	
	@Test
	public <T> void deleteGraphTest() {
		List<T> data = new ArrayList<T>();
		List<T> attributes = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		Graph graph = new Graph("1", "bar", 300, 300, data, color, "test user", "the test graph", attributes);

		graphService.deleteGraph(graph);
		verify(graphRepository, times(1)).delete(graph);
	}
	
	@Test
	public <T> void getGraphByIdTest() {
		List<T> data = new ArrayList<T>();
		List<T> attributes = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		Optional<Graph> graph = null;

		when(graphRepository.findById("1")).thenReturn(graph); 

		assertEquals(graph, graphService.getGraphById("1"));
	}
	
	@Test
	public <T> void getGraphByTypeTest() {
		List<T> data = new ArrayList<T>();
		List<T> attributes = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		Graph graph = null;
		
		graph = new Graph("1", "bar", 300, 300, data, color, "test user", "the test graph", attributes);

		when(graphRepository.findByType("bar")).thenReturn(graph); 

		assertEquals(graph, graphService.getGraphByType("bar"));
	}
	
	@Test
	public <T> void getGraphByOwnerTest() {
		List<T> data = new ArrayList<T>();
		List<T> attributes = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		Graph graph = null;
		
		graph = new Graph("1", "bar", 300, 300, data, color, "test user", "the test graph", attributes);

		when(graphRepository.findByOwner("test user")).thenReturn(graph); 

		assertEquals(graph, graphService.getGraphByOwner("test user"));
	}
}
