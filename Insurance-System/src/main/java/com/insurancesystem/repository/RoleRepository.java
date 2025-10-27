package com.insurancesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurancesystem.entity.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	//not visible

}
