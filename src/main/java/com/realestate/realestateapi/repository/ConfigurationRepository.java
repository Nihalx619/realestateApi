package com.realestate.realestateapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.realestateapi.entity.Configuration;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
	List<Configuration> findByProjectId(Long projectId);
}