package com.realestate.realestateapi.dto;

import java.time.LocalDateTime;

public class EnquiryDto {

	private Long id;
	private Long projectId;
	private String name;
	private String phone;
	private String email;
	private String message;
	private String status;
	private LocalDateTime createdAt;

	public EnquiryDto() {
	}

	public EnquiryDto(Long id, Long projectId, String name, String phone, String email, String message, String status,
			LocalDateTime createdAt) {
		this.id = id;
		this.projectId = projectId;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.message = message;
		this.status = status;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}