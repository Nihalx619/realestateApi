package com.realestate.realestateapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.realestateapi.entity.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {
	Optional<Agent> findByEmail(String email);
}
