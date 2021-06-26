package es.uv.tfm.graphicservice;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.uv.tfm.graphicservice.GraphicserviceApplication;
import es.uv.tfm.graphicservice.controller.GraphController;
import es.uv.tfm.graphicservice.model.Graph;
import es.uv.tfm.graphicservice.service.GraphService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers=GraphController.class)
@ContextConfiguration(classes={GraphicserviceApplication.class})
public class GraphControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	
	@Autowired
	private MockMvc mvc;
	
	@SuppressWarnings("rawtypes")
	@MockBean
	private GraphService graphService;
	
	@InjectMocks
	private GraphController graphController;
	
	@Before
	public void setup() {
		initMocks(this);
		this.mvc = MockMvcBuilders.standaloneSetup(graphController).build();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public <T> void shouldReturnListOfGraphs()
	  throws Exception {
	    
		List<T> data = new ArrayList<T>();
		List<T> attributes = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		Graph graph = new Graph("1", "bar", 300, 300, data, color, "test user", "the test graph", attributes);
		Graph graph2 = new Graph("2", "pie", 300, 300, data, color, "test user", "the test graph 2", attributes);

	    List<Graph> allGraphs = new ArrayList<Graph>();
	    allGraphs.add(graph);
	    allGraphs.add(graph2);
	    
	    when(graphService.getGraphs()).thenReturn(allGraphs);

	    mvc.perform(get("/graph/")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$[0].id", is(graph.getId())))
	      .andExpect(jsonPath("$[1].id", is(graph2.getId())))
	      ;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public <T> void shouldCreateGraph()throws Exception {
	    
		List<T> data = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		List<T> attributes = new ArrayList<T>();

		Graph graph = new Graph();
		graph = new Graph("1", "bar", 300, 300, data, color, "test user", "the test graph", attributes);

	    when(graphService.saveGraph(graph)).thenReturn(graph);
	    
	    String json = mapper.writeValueAsString(graph);

	    mvc.perform(post("/graph/")
	  	  .contentType(MediaType.APPLICATION_JSON)
	      .content(json))
	      .andExpect(status().isCreated());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public <T> void shouldReturnGraphById()throws Exception {
	    
		List<T> data = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		List<T> attributes = new ArrayList<T>();

		Graph graph = new Graph();
		String id = "1";
		graph = new Graph(id, "bar", 300, 300, data, color, "test user", "the test graph", attributes);

	    when(graphService.getGraphById(id)).thenReturn(Optional.of(graph));
	    
	    mvc.perform(get("/graph/" + id)
	  	  .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isFound())
	      .andExpect(jsonPath("$.id", is(graph.getId())));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public <T> void shouldReturnGraphByOwner()throws Exception {
	    
		List<T> data = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		List<T> attributes = new ArrayList<T>();

		Graph graph = new Graph();
		String owner = "test user";
		graph = new Graph("1", "bar", 300, 300, data, color, owner, "the test graph", attributes);

	    when(graphService.getGraphByOwner(owner)).thenReturn(graph);
	    
	    mvc.perform(get("/graph/owner/" + owner)
	  	  .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isFound())
	      .andExpect(jsonPath("$.owner", is(graph.getOwner())));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public <T> void shouldReturnGraphByType()throws Exception {
	    
		List<T> data = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		List<T> attributes = new ArrayList<T>();

		Graph graph = new Graph();
		String type = "bar";
		graph = new Graph("1", type, 300, 300, data, color, "test user", "the test graph", attributes);

	    when(graphService.getGraphByType(type)).thenReturn(graph);
	    
	    mvc.perform(get("/graph/type/" + type)
	  	  .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isFound())
	      .andExpect(jsonPath("$.type", is(graph.getType())));
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public <T> void shouldUpdateGraph()throws Exception {
	    
		List<T> data = new ArrayList<T>();
		List<String> color = new ArrayList<String>();
		List<T> attributes = new ArrayList<T>();

		String id = "1";
		Graph graph = new Graph(id, "bar", 300, 300, data, color, "test user", "the test graph", attributes);

	    when(graphService.updateGraph(id, graph)).thenReturn(graph);
	    
	    String json = mapper.writeValueAsString(graph);

	    mvc.perform(put("/graph/" + id)
	  	  .contentType(MediaType.APPLICATION_JSON).content(json))
	      .andExpect(status().isOk());
	}
	

	@Test
	public void shouldDeleteGraph()throws Exception {
	    
		String id = "1";
	    
	    mvc.perform(delete("/graph/" + id)).andExpect(status().isOk());
	}
}
