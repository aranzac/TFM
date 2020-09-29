package es.uv.tfm.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.uv.tfm.userservice.entities.User;
import es.uv.tfm.userservice.exceptions.ResourceNotFoundException;
import es.uv.tfm.userservice.exceptions.UserExistsException;
import es.uv.tfm.userservice.services.UserService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public ResponseEntity<Object> getAll() {	
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);		 
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public User getAccount(@PathVariable("id") int id) {	
		try {
			return userService.findById(id);
		} catch (ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/")
	public  User createUser(@RequestBody User user) {
	
		try {
			return userService.createUser(user);
		} catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/block/{id}")
	public User blockUser(@PathVariable("id") int id) {
		
		try {
			
			User user = userService.findById(id);
			
			if (user.getState().equalsIgnoreCase("enabled"))
				user.setState("disabled");
			
			else if (user.getState().equalsIgnoreCase("disabled"))
				user.setState("enabled");
			
			return userService.updateUser(id, user);
		} catch (UserExistsException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
		
		try {
			return new ResponseEntity<Object>(userService.deleteUser(id), HttpStatus.NO_CONTENT);
		} catch (ResourceNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}
	
}