package com.insurancesystem.service;
/**
 * @author Nikita Mahajan
 * @since 2025-10-20
 * @description Service interface for defining business operations related to Role.
 */
import java.util.List;

import com.insurancesystem.entity.Role;

public interface RoleService {
	
	public List<Role> findAllRole();

}
