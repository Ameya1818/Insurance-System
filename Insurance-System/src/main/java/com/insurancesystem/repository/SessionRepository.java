package com.insurancesystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insurancesystem.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

	Optional<Session> findByToken(String token);
}
