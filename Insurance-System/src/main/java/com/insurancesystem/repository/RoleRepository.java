package com.insurancesystem.repository;
/**
 * @author Nikita Mahajan
 * @since 2025-10-20
 * @description Repository for performing database operations on Role.
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.insurancesystem.entity.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
