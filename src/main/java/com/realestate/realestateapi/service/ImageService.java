package com.realestate.realestateapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.realestate.realestateapi.dto.ImageDto;
import com.realestate.realestateapi.entity.Image;
import com.realestate.realestateapi.entity.Project;
import com.realestate.realestateapi.repository.ImageRepository;
import com.realestate.realestateapi.repository.ProjectRepository;

@Service
public class ImageService {

	private final ProjectRepository projectRepository;
	private final ImageRepository imageRepository;

	public ImageService(ProjectRepository projectRepository, ImageRepository imageRepository) {
		this.projectRepository = projectRepository;
		this.imageRepository = imageRepository;
	}

	// 5 methods
	public List<ImageDto> getImagesByProject(Long projectId) {
		return imageRepository.findByProjectId(projectId).stream().map(image -> mapToDto(image))
				.collect(Collectors.toList());
	}

	public ImageDto getImageById(Long id) {

		Image image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
		return mapToDto(image);
	}

	public ImageDto createImage(ImageDto dto) {
		Image image = mapToImage(dto);
		Image savedImage = imageRepository.save(image);
		return mapToDto(savedImage);
	}

	public ImageDto updateImage(Long id, ImageDto dto) {
		Image existing = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
		Image updated = mapToImage(dto);
		updated.setId(existing.getId());
		return mapToDto(imageRepository.save(updated));
	}

	public void deleteImage(Long id) {
		Image image = imageRepository.findById(id).orElseThrow(() -> new RuntimeException("Image not found"));
		imageRepository.delete(image);
	}

	// mappers

	private ImageDto mapToDto(Image image) {
		ImageDto dto = new ImageDto();
		dto.setId(image.getId());
		dto.setProjectId(image.getProject().getId());
		dto.setUrl(image.getUrl());
		dto.setCaption(image.getCaption());
		dto.setPrimary(image.isPrimary());
		return dto;
	}

	private Image mapToImage(ImageDto dto) {
		Image image = new Image();
		image.setUrl(dto.getUrl());
		image.setCaption(dto.getCaption());
		image.setPrimary(dto.isPrimary());
		Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(() -> new RuntimeException("Project not found"));
		image.setProject(project);
		return image;
	}
}