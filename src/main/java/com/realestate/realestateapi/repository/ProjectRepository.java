package com.realestate.realestateapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.realestateapi.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
      
}
