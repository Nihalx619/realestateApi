package com.realestate.realestateapi.dto;

public class ImageDto {
	
	// no-args
	public ImageDto() {}

	// all-args
	public ImageDto(Long id, Long projectId, String url, String caption, boolean isPrimary) {
	    this.id = id;
	    this.projectId = projectId;
	    this.url = url;
	    this.caption = caption;
	    this.isPrimary = isPrimary;
	}
	private Long id;
	private Long projectId;
	private String url;
	private String caption;
	private boolean isPrimary;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public boolean isPrimary() {
		return isPrimary;
	}
	public void setPrimary(boolean isPrimary) {
		this.isPrimary = isPrimary;
	}
	
}
