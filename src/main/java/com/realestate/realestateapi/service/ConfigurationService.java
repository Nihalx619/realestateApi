package com.realestate.realestateapi.service;
import com.realestate.realestateapi.exception.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.realestate.realestateapi.dto.ConfigurationDto;
import com.realestate.realestateapi.entity.Configuration;
import com.realestate.realestateapi.entity.Project;
import com.realestate.realestateapi.repository.ConfigurationRepository;
import com.realestate.realestateapi.repository.ProjectRepository;

@Service
public class ConfigurationService {
	private final ProjectRepository projectRepository;
	private final ConfigurationRepository configurationRepository;

	public ConfigurationService(ProjectRepository projectRepository, ConfigurationRepository configurationRepository) {

		this.projectRepository = projectRepository;
		this.configurationRepository = configurationRepository;
	}

	public List<ConfigurationDto> getConfigurationsByProject(Long projectId) {
		List<Configuration> configurations = configurationRepository.findByProjectId(projectId);

		return configurations.stream().map(config -> mapToDto(config)).collect(Collectors.toList());

	}

	public ConfigurationDto getConfigurationById(Long id) {
		Configuration configuration = configurationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("configuration not found"));
		return mapToDto(configuration);
	}

	public ConfigurationDto createConfiguration(ConfigurationDto dto) {
		Configuration configuration = mapToConfiguration(dto);
		Configuration savedConfiguration = configurationRepository.save(configuration);
		return mapToDto(savedConfiguration);

	}

	public ConfigurationDto updateConfiguration(Long id, ConfigurationDto dto) {
       Configuration existingConfiguration = configurationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("configuration not found") );
       Configuration updatedConfiguration = mapToConfiguration(dto);
       updatedConfiguration.setId(existingConfiguration.getId());
       
       Configuration savedConfiguration =  configurationRepository.save(updatedConfiguration);
       
       ConfigurationDto configurationDto = mapToDto(savedConfiguration);
       
       return configurationDto;
	}

	public void deleteConfiguration(Long id) {
	    Configuration configuration = configurationRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Configuration not found"));
	    configurationRepository.delete(configuration);
	}

	private ConfigurationDto mapToDto(Configuration configuration) {
		ConfigurationDto dto = new ConfigurationDto();
		dto.setId(configuration.getId());
		dto.setProjectId(configuration.getProject().getId());
		dto.setBhkType(configuration.getBhkType());
		dto.setCarpetAreaSqft(configuration.getCarpetAreaSqft());
		dto.setPrice(configuration.getPrice());
		return dto;
	}

	private Configuration mapToConfiguration(ConfigurationDto dto) {
		Configuration configuration = new Configuration();
		configuration.setBhkType(dto.getBhkType());
		configuration.setCarpetAreaSqft(dto.getCarpetAreaSqft());
		configuration.setPrice(dto.getPrice());

		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));
		configuration.setProject(project);

		return configuration;
	}

}
