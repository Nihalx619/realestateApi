package com.realestate.realestateapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realestate.realestateapi.dto.ImageDto;
import com.realestate.realestateapi.service.ImageService;

@RestController
@RequestMapping("/api/images")
public class ImageController {

	private final ImageService imageService;

	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}

	@GetMapping("/project/{projectId}")
	public ResponseEntity<List<ImageDto>> getByProject(@PathVariable Long projectId) {

		return ResponseEntity.ok(imageService.getImagesByProject(projectId));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ImageDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(imageService.getImageById(id));
	}

	@PostMapping
	public ResponseEntity<ImageDto> create(@RequestBody ImageDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(imageService.createImage(dto));

	}

	@PutMapping("/{id}")
	public ResponseEntity<ImageDto> update(@PathVariable Long id, @RequestBody ImageDto dto) {
		return ResponseEntity.ok(imageService.updateImage(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
       imageService.deleteImage(id);
       return ResponseEntity.noContent().build();
	}
}