package com.realestate.realestateapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.realestateapi.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
	List<Image> findByProjectId(Long projectId);
}
