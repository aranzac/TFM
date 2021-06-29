package es.uv.tfm.userservice.test;

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
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.uv.tfm.userservice.controller.AccountController;
import es.uv.tfm.userservice.entities.User;
import es.uv.tfm.userservice.security.JwtUtil;
import es.uv.tfm.userservice.services.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AccountControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	@InjectMocks
	private AccountController accountController;
	
	@Mock
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Before
	public void setup() {
		initMocks(this);
		this.mvc = MockMvcBuilders.standaloneSetup(accountController).build();
	}
	
	
	@Test
	public void shouldReturnListOfUsers() throws Exception {
		
		User user = new User(9999, "test", "prueba1234", "test@test", true);
		User user2 = new User(9990, "test2", "prueba1234", "test2@test", true);

	    List<User> allUsers = new ArrayList<User>();
	    allUsers.add(user);
	    allUsers.add(user2);
	    
	    when(userService.getUsers()).thenReturn(allUsers);

	    mvc.perform(get("/account/")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$[0].id", is(user.getId())))
	      .andExpect(jsonPath("$[1].id", is(user2.getId())))
	      ;
	}
	
	
	@Test
	public void shouldReturnUserById()throws Exception {
	    
		int id = 9999;
		User user = new User(id, "test", "prueba1234", "test@test", true);

	    when(userService.findById(id)).thenReturn(user);
	    
	    mvc.perform(get("/account/" + id)
	  	  	  .contentType(MediaType.APPLICATION_JSON))
	  	      .andExpect(status().isFound())
	  	      .andExpect(jsonPath("$.id", is(user.getId())));
	}
	
	
	@WithMockUser(username = "admin", authorities = "ADMIN")
	@Test
	public void shouldReturnUserByUsername()throws Exception {

	    
		String username = "test";
		User user = new User(9999, username, "prueba1234", "test@test", true);

	    when(userService.findByUsername(username)).thenReturn(user);
	    
		JwtUtil jwtUtil = new JwtUtil();
		String token = jwtUtil.generateToken("test");
	    
	    mvc.perform(get("/account/user/" + username)
  	  	  .contentType(MediaType.APPLICATION_JSON).header("authorization", "Bearer " + token))
  	      .andExpect(status().isOk())
  	      .andExpect(jsonPath("$.id", is(user.getId())));
	}
	
	@Test
	public void shouldCreateUser()throws Exception {

		String username = "test";
		User user = new User(9999, username, "prueba1234", "test@test", true);

	    when(userService.createUser(user)).thenReturn(user);
	    when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("*****");
	    
	    String json = mapper.writeValueAsString(user);

	    mvc.perform(post("/account/")
  	  	  .contentType(MediaType.APPLICATION_JSON).content(json))
  	      .andExpect(status().isCreated());
	}
	
	@Test
	@WithMockUser(username = "admin", authorities = "ADMIN")
	public void shouldBlockUser() throws Exception {

		int id = 9999;
		User user = new User(id, "test", "prueba1234", "test@test", true);

	    when(userService.findById(id)).thenReturn(user);
	    when(userService.updateUser(id, user)).thenReturn(user);

	    mvc.perform(put("/account/block/" + id)
  	  	  .contentType(MediaType.APPLICATION_JSON))
  	      .andExpect(status().isOk());
	}
	
	
	@Test
	@WithMockUser(username = "admin", authorities = "ADMIN")
	public void shouldDeleteUser()throws Exception {
	    
		int id = 9999;
		User user = new User(id, "test", "prueba1234", "test@test", true);

		MockHttpServletRequest request = new MockHttpServletRequest();
		
	    when(userService.findById(id)).thenReturn(user);

		JwtUtil jwtUtil = new JwtUtil();
		String token = jwtUtil.generateToken("test");
			    
	    mvc.perform(delete("/account/" + id).header("authorization", "Bearer " + token)).andExpect(status().isNoContent());
	}
	

}
