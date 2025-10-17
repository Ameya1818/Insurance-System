package com.insurancesystem.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insurancesystem.entity.User;
import com.insurancesystem.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/saveUser")
	public User saveUser(@RequestBody User user) {
		User user1 = userService.saveUser(user);
		return user1;
	}

}
