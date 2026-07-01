package com.realestate.realestateapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.realestateapi.dto.EnquiryDto;
import com.realestate.realestateapi.service.EnquiryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enquiries")
public class EnquiryController {

    private final EnquiryService enquiryService;

    public EnquiryController(EnquiryService enquiryService) {
        this.enquiryService = enquiryService;
    }

    @PostMapping
    public ResponseEntity<EnquiryDto> create(@Valid @RequestBody EnquiryDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enquiryService.createEnquiry(dto));
    }

    @GetMapping
    public ResponseEntity<List<EnquiryDto>> getAll() {
        return ResponseEntity.ok(enquiryService.getAllEnquiries());
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<EnquiryDto>> getByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(enquiryService.getEnquiriesByProject(projectId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<EnquiryDto>> getByStatus(@PathVariable String status) {
       return ResponseEntity.ok(enquiryService.getEnquiriesByStatus(status));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EnquiryDto> updateStatus(@PathVariable Long id, @RequestBody String status) {
    	return ResponseEntity.ok(enquiryService.updateEnquiryStatus(id, status));
    }
}