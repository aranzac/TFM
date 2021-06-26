package es.uv.tfm.userservice.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.uv.tfm.userservice.UserserviceApplication;
import es.uv.tfm.userservice.controller.RoleController;
import es.uv.tfm.userservice.entities.Role;
import es.uv.tfm.userservice.services.RoleService;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class RoleControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private RoleService roleService;
	
	@InjectMocks
	private RoleController roleController;
	
	@Before
	public void setup() {
		initMocks(this);
		this.mvc = MockMvcBuilders.standaloneSetup(roleController).build();
	}
	
	
	@Test
	public void shouldReturnListOfRoles() throws Exception {
		
		Role role1 = new Role(0, "ROLE_USER");
		Role role2 = new Role(1, "ROLE_ADMIN");
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(role1);
		roles.add(role2);
		
		when(roleService.getRoles()).thenReturn(roles);
		
		mvc.perform(get("/roles/")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$[0].id", is(role1.getId())))
	      .andExpect(jsonPath("$[1].id", is(role2.getId())))
	      ;
	}
	
	@Test
	public void shouldCreateRole()throws Exception {
	    
		Role role = new Role(3, "ROLE_TEST");
		
	    when(roleService.createRole(role)).thenReturn(role);
	    
	    String json = mapper.writeValueAsString(role);

	    mvc.perform(post("/roles/")
	  	  .contentType(MediaType.APPLICATION_JSON)
	      .content(json))
	      .andExpect(status().isCreated());
	}

	
	@Test
	public void shouldReturnRoleById()throws Exception {
	    
		int id = 1;
		Role role = new Role(id, "ROLE_TEST");

	    when(roleService.findById(id)).thenReturn(role);
	    
	    mvc.perform(get("/roles/" + id)
	  	  .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isFound())
	      .andExpect(jsonPath("$.id", is(role.getId())));
	}
	
	@Test
	public void shouldReturnRoleByName()throws Exception {
	    
		String name = "ROLE_TEST";
		Role role = new Role(1, name);

	    when(roleService.findByName(name)).thenReturn(role);
	    
	    mvc.perform(get("/roles/name/" + name)
	  	  .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isFound())
	      .andExpect(jsonPath("$.name", is(role.getName())));
	}


	
	@Test
	@WithMockUser(username = "admin", authorities = "ADMIN")
	public void shouldGetRoleById() throws Exception {
	   
		int id = 1;
		Role role = new Role(1, "ROLE_TEST");

	    when(roleService.findById(id)).thenReturn(role);
	    
	    mvc.perform(get("/roles/" + id)
	  	  	  .contentType(MediaType.APPLICATION_JSON))
	  	      .andExpect(status().isFound())
	  	      .andExpect(jsonPath("$.id", is(role.getId())));
	}
	
	
	
	@Test
	@WithMockUser(username = "admin", authorities = "ADMIN")
	public void shouldDeleteRole()throws Exception {
	    
		String id = "1";
	    
	    mvc.perform(delete("/roles/" + id)).andExpect(status().isOk());
	}
	
}
