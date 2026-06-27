package com.realestate.realestateapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.realestateapi.dto.ConfigurationDto;
import com.realestate.realestateapi.service.ConfigurationService;

@RestController
@RequestMapping("/api/configurations")
public class ConfigurationController {
	// GET all configs for a project — custom path
	
	
	
	
	private final ConfigurationService configurationService;

	public ConfigurationController(ConfigurationService configurationService) {
	    this.configurationService = configurationService;
	}



	@GetMapping("/project/{projectId}")
	public ResponseEntity<List<ConfigurationDto>> getByProject(@PathVariable Long projectId){
		
		return ResponseEntity.ok(configurationService.getConfigurationsByProject(projectId));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ConfigurationDto> getById(@PathVariable Long id){
		return ResponseEntity.ok(configurationService.getConfigurationById(id));
	}

	@PostMapping
	public ResponseEntity<ConfigurationDto> create(@RequestBody ConfigurationDto dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(configurationService.createConfiguration(dto));
		
	}
	
	// PUT update
	@PutMapping("/{id}")
	public ResponseEntity<ConfigurationDto> update(@PathVariable Long id, @RequestBody ConfigurationDto dto){
		return ResponseEntity.ok(configurationService.updateConfiguration(id, dto));
	}
	
	// DELETE
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		configurationService.deleteConfiguration(id);
		return ResponseEntity.noContent().build();
	}
}
