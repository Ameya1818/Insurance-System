package com.insurancesystem.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.insurancesystem.entity.Role;
import com.insurancesystem.exception.RoleNotFoundException;
import com.insurancesystem.repository.RoleRepository;

@RestController
public class RoleRestController {
	
	@Autowired
	private RoleRepository roleRepository;
		
	@GetMapping("/findAllRole/role")
	public List<Role> findAllRole(){
		
		List<Role> roles=roleRepository.findAll();
		if(roles.isEmpty()) {
			throw new RoleNotFoundException("No roles found in the system");
		}
		return roles;
		
	}

}
