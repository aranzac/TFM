package es.uv.tfm.userservice.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private int id;
	
	@Column(name= "username")
	private String username;
	
	@Column(name= "password")
	private String password;
	
	@Column(name= "email")
	private String email;
	
	@Column(name= "state")
	private String state;

	@ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
	@JoinTable(name = "users_has_roles", joinColumns = @JoinColumn(name = "users_id"), inverseJoinColumns = @JoinColumn(name = "roles_id"))
	private Set<Role> roles;
	
	
	public User() {
		super();
	}
	
	public User(int id, String username, String password, String email, String state) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.state = state;

	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}



}
