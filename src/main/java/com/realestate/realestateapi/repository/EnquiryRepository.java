package com.realestate.realestateapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.realestateapi.entity.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry, Long> {
	List<Enquiry> findByProjectId(Long projectId);
	List<Enquiry> findByStatus(String status);
	
	
}
