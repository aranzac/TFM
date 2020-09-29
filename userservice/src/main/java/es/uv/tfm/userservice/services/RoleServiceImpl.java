package es.uv.tfm.userservice.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uv.tfm.userservice.entities.Role;
import es.uv.tfm.userservice.exceptions.ResourceNotFoundException;
import es.uv.tfm.userservice.exceptions.UserExistsException;
import es.uv.tfm.userservice.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepository;
	
	
	@Override
	public Role createRole(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public Role updateRole(int id, Role role) {
		roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No user found with id " + id));
		
		role.setId(id);
		
		return roleRepository.save(role);
	}

	@Override
	public Boolean deleteRole(int id) {
		
		try {
			 roleRepository.deleteById(id);
				return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Role findById(int id) {
		return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No user found with id " + id));

	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundException("No user found with Name " + name));

	}

	@Override
	public List<Role> getRoles(){
		return roleRepository.findAll();
	}
	
	
}
