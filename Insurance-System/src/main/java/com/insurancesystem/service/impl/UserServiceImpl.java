package com.insurancesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insurancesystem.entity.User;
import com.insurancesystem.repository.UserRepository;
import com.insurancesystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		User user1 = userRepository.save(user);
		return user1;
	}

}
