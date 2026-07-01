package com.realestate.realestateapi.service;
import com.realestate.realestateapi.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.realestate.realestateapi.dto.EnquiryDto;
import com.realestate.realestateapi.entity.Enquiry;
import com.realestate.realestateapi.entity.Project;
import com.realestate.realestateapi.repository.EnquiryRepository;
import com.realestate.realestateapi.repository.ProjectRepository;


@Service
public class EnquiryService {
	
	private final EnquiryRepository enquiryRepository;
	private final ProjectRepository projectRepository;
	private final EmailService emailService;

	public EnquiryService(EnquiryRepository enquiryRepository, 
	                      ProjectRepository projectRepository,
	                      EmailService emailService) {
	    this.enquiryRepository = enquiryRepository;
	    this.projectRepository = projectRepository;
	    this.emailService = emailService;
	}
	
	public EnquiryDto createEnquiry(EnquiryDto dto) {
	    Enquiry enquiry = mapToEnquiry(dto);
	    Enquiry savedEnquiry = enquiryRepository.save(enquiry);
	    
	    // send notification email (won't crash if it fails)
	    emailService.sendEnquiryNotification(savedEnquiry);
	    
	    return mapToDto(savedEnquiry);
	}

	public List<EnquiryDto> getAllEnquiries() {
	  
		return enquiryRepository.findAll().stream().map(enquiry -> mapToDto(enquiry)).collect(Collectors.toList());
	}

	public List<EnquiryDto> getEnquiriesByProject(Long projectId) {
	    // your code here
		return enquiryRepository.findByProjectId(projectId).stream().map(enquiry -> mapToDto(enquiry)).collect(Collectors.toList());
		
	}

	public List<EnquiryDto> getEnquiriesByStatus(String status) {
	   
		return enquiryRepository.findByStatus(status).stream().map(enquiry -> mapToDto(enquiry)).collect(Collectors.toList());
		
	}

	public EnquiryDto updateEnquiryStatus(Long id, String status) {
	    // your code here
		
		Enquiry enquiry = enquiryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enquiry not found"));
		
		enquiry.setStatus(status);
		return mapToDto(enquiryRepository.save(enquiry));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private EnquiryDto mapToDto(Enquiry enquiry) {
	    EnquiryDto dto = new EnquiryDto();
	    dto.setId(enquiry.getId());
	    dto.setProjectId(enquiry.getProject().getId());
	    dto.setName(enquiry.getName());
	    dto.setPhone(enquiry.getPhone());
	    dto.setEmail(enquiry.getEmail());
	    dto.setMessage(enquiry.getMessage());
	    dto.setStatus(enquiry.getStatus());
	    dto.setCreatedAt(enquiry.getCreatedAt());
	    return dto;
	}

	private Enquiry mapToEnquiry(EnquiryDto dto) {
	    Enquiry enquiry = new Enquiry();
	    enquiry.setName(dto.getName());
	    enquiry.setPhone(dto.getPhone());
	    enquiry.setEmail(dto.getEmail());
	    enquiry.setMessage(dto.getMessage());
	    Project project = projectRepository.findById(dto.getProjectId())
	            .orElseThrow(() -> new ResourceNotFoundException("Project not found"));
	    enquiry.setProject(project);
	    return enquiry;
	}
}
