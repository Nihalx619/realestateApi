package com.realestate.realestateapi.entity;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String builder;

	@Column(nullable = false)
	private String locality;

	private String videoUrl;
	private String description;
	private String reraNumber;
	
	@Column(nullable = false)
	private String status;
	
	private LocalDate possessionDate;
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Image> images;

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Configuration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<Configuration> configurations) {
		this.configurations = configurations;
	}

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
	
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Configuration> configurations;

}
