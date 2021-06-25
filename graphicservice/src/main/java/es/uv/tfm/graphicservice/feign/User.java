package es.uv.tfm.graphicservice.feign;

import java.util.List;

import org.springframework.data.annotation.Id;

public class User {

	@Id
	private int id;
	
	private String username;
	
	private String password;
	
	private String email;
	
	private Boolean enabled;

	private List<Role> roles;
	
}
