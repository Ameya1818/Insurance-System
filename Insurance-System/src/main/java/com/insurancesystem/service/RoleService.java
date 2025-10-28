package com.insurancesystem.service;

import java.util.List;
import com.insurancesystem.entity.Role;


public interface RoleService {

    // Add a new role
    Role addRole(Role role);

    // Retrieve all roles
    List<Role> findAllRole();
}
