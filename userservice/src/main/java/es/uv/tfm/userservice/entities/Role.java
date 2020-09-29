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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id") 
	private int id;
	
	@Column(name= "name")
	private String name;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY ,cascade = {CascadeType.ALL})
	@JoinTable(name = "users_has_roles", joinColumns = @JoinColumn(name = "roles_id"), inverseJoinColumns = @JoinColumn(name = "users_id"))
	private Set<User> users;
	
	
	public Role() {
		super();
	}

	public Role( String name, Set<User> users) {
		super();
		this.name = name;
		this.users = users;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
}
