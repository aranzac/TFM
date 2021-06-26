package es.uv.tfm.userservice.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.uv.tfm.userservice.controller.AuthController;
import es.uv.tfm.userservice.entities.AuthRequest;
import es.uv.tfm.userservice.entities.User;
import es.uv.tfm.userservice.services.UserService;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AuthControllerTest {

	
	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mvc;
	
    @Mock
	Authentication authentication;
	
	@MockBean
	private UserService userService;
	
	@InjectMocks
	private AuthController authController;
	
	@Mock
	AuthenticationManager authenticationManager;
	
	
	@Before
	public void setup() {
		initMocks(this);
		this.mvc = MockMvcBuilders.standaloneSetup(authController).build();
	}
	
	
	@Test
	public void shouldReturnUserByUsername() throws Exception {
				
//		AuthRequest authRequest = new AuthRequest();
//		authRequest.setUsername("prueba");
//		authRequest.setPassword("prueba1234");
//	    
//		int id = 1;
//		String username = "prueba";
//		User user = new User(9999, username, "prueba1234", "test@test", true);
//
//	    when(userService.findByUsername(username)).thenReturn(user);
//        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()))).thenReturn(authentication);
//
//        authenticationManager = mock(AuthenticationManager.class);        
//	    	
//	    String json = mapper.writeValueAsString(authRequest);
//
//	    mvc.perform(post("/auth/authenticate/")
//	  	  .contentType(MediaType.APPLICATION_JSON).content(json))
//	      .andExpect(status().isOk())
//	      .andExpect(jsonPath("$.username", is(user.getUsername())));
	}
		
}
