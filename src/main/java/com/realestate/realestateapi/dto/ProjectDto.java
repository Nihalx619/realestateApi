package com.realestate.realestateapi.dto;

import java.time.LocalDate;

public class ProjectDto {

	private Long id;
	private String name;
	private String builder;
	private String locality;
	private String videoUrl;
	private String description;
	private String reraNumber;
	private String status;
	private LocalDate possessionDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBuilder() {
		return builder;
	}

	public void setBuilder(String builder) {
		this.builder = builder;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReraNumber() {
		return reraNumber;
	}

	public void setReraNumber(String reraNumber) {
		this.reraNumber = reraNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getPossessionDate() {
		return possessionDate;
	}

	public void setPossessionDate(LocalDate possessionDate) {
		this.possessionDate = possessionDate;
	}

	public ProjectDto(Long id, String name, String builder, String locality, String videoUrl, String description,
			String reraNumber, String status, LocalDate possessionDate) {
		super();
		this.id = id;
		this.name = name;
		this.builder = builder;
		this.locality = locality;
		this.videoUrl = videoUrl;
		this.description = description;
		this.reraNumber = reraNumber;
		this.status = status;
		this.possessionDate = possessionDate;
	}
    
	
	public ProjectDto() {
	}
	
	
}