package com.insurancesystem.repository;

/**
 * @author Viraj Akte
 * @since 2025-10-20
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.insurancesystem.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	
}
