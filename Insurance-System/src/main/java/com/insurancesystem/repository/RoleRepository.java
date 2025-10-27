package com.insurancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.insurancesystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
