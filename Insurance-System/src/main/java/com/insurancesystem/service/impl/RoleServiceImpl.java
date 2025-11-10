package com.insurancesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurancesystem.entity.Role;
import com.insurancesystem.repository.RoleRepository;
import com.insurancesystem.service.RoleService;
/**
 * @author Nikita Mahajan
 * @since 2025-10-20
 * @description Service implementation class that handles the business logic for Role.
 */
@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	@Override
	public List<Role> findAllRole() {
		List<Role> roles=roleRepository.findAll();
		return roles;
	}

}
