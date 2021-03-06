package es.uv.tfm.userservice.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import es.uv.tfm.userservice.entities.Role;
import es.uv.tfm.userservice.entities.User;

public class UserDetailsImpl implements UserDetails{

	
	private User user;
	
	private static final long serialVersionUID = 1L;

	private int id;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	private Collection<GrantedAuthority> authorities;
	
	  public UserDetailsImpl(User user) {
	        this.user = user;
	    }
	

	public UserDetailsImpl(int id, String username, String email, String password,
			Collection<GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	
	
//	public static UserDetailsImpl build(User user) {
////		List<GrantedAuthority> authorities = user.getRoles().stream()
////				.map(role -> new SimpleGrantedAuthority(role.getName()))
////				.collect(Collectors.toList());
////		
//		return new UserDetailsImpl(
//				user.getId(), 
//				user.getUsername(), 
//				user.getEmail(),
//				user.getPassword(), 
//				authorities);
//	}
	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return authorities;
//	}
	
	 @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
		 	List<Role> roles = user.getRoles();
	        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	         
	        for (Role role : roles) {
	            authorities.add(new SimpleGrantedAuthority(role.getName()));
	        }
	         
	        return authorities;
	    }
	

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
	
	
	
}
