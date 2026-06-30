package com.realestate.realestateapi.service;
import com.realestate.realestateapi.exception.ResourceNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.realestate.realestateapi.dto.ProjectDto;
import com.realestate.realestateapi.entity.Project;
import com.realestate.realestateapi.repository.ProjectRepository;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public List<ProjectDto> getAllProjects() {

		return projectRepository.findAll().stream().map(project -> mapToProjectDto(project))
				.collect(Collectors.toList());

	}

	public ProjectDto getProjectById(Long id) {
		Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
		return mapToProjectDto(project);
	}

	public ProjectDto createProject(ProjectDto projectDto) {

		Project project = maptoProject(projectDto);
		Project savedProject = projectRepository.save(project);
		return mapToProjectDto(savedProject);

	}

	public ProjectDto updateProject(Long id, ProjectDto projectDto) {
		Project existingProject = projectRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Project not found"));

		Project updatedProject = maptoProject(projectDto);
		updatedProject.setId(existingProject.getId());

		Project savedProject = projectRepository.save(updatedProject);
		return mapToProjectDto(savedProject);
	}
	
	public void deleteProject(Long id) {
          Project project = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found"));
          projectRepository.delete(project);
	}

	private ProjectDto mapToProjectDto(Project project) {
		ProjectDto projectDto = new ProjectDto();
		projectDto.setId(project.getId());
		projectDto.setName(project.getName());
		projectDto.setBuilder(project.getBuilder());
		projectDto.setLocality(project.getLocality());
		projectDto.setVideoUrl(project.getVideoUrl());
		projectDto.setDescription(project.getDescription());
		projectDto.setReraNumber(project.getReraNumber());
		projectDto.setStatus(project.getStatus());
		projectDto.setPossessionDate(project.getPossessionDate());

		return projectDto;
	}

	private Project maptoProject(ProjectDto projectDto) {

		Project project = new Project();

		project.setName(projectDto.getName());
		project.setBuilder(projectDto.getBuilder());
		project.setLocality(projectDto.getLocality());
		project.setVideoUrl(projectDto.getVideoUrl());
		project.setDescription(projectDto.getDescription());
		project.setReraNumber(projectDto.getReraNumber());
		project.setStatus(projectDto.getStatus());
		project.setPossessionDate(projectDto.getPossessionDate());

		return project;

	}

}
