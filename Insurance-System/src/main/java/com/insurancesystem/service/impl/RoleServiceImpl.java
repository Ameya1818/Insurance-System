package com.insurancesystem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.insurancesystem.entity.Role;
import com.insurancesystem.repository.RoleRepository;
import com.insurancesystem.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }
}
