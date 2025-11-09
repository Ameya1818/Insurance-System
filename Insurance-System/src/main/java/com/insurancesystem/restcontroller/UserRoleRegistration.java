package com.insurancesystem.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.insurancesystem.entity.Role;
import com.insurancesystem.entity.UserRegistration;
import com.insurancesystem.service.RoleService;

@RestController
@RequestMapping("/api")
public class UserRoleRegistration {

	@Autowired
	private RoleService roleService;

	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */

	@PostMapping("/register")
	public ResponseEntity<Role> saveRoleUser(@RequestBody Role role) {
		if (role.getUserList() != null) {
			for (UserRegistration user : role.getUserList()) {
				//user.setPassword(passwordEncoder.encode(user.getPassword())); // encode password

				user.setRole(role);

			}
		}
		Role savedRole = roleService.addRole(role);
		return ResponseEntity.ok(savedRole);
	}
}
