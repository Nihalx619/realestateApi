package com.realestate.realestateapi.dto;

import java.math.BigDecimal;

public class ConfigurationDto {

    private Long id;
    private Long projectId;
    private String bhkType;
    private Integer carpetAreaSqft;
    private BigDecimal price;

    // no-args constructor
    public ConfigurationDto() {}

    // all-args constructor
    public ConfigurationDto(Long id, Long projectId, String bhkType, 
                            Integer carpetAreaSqft, BigDecimal price) {
        this.id = id;
        this.projectId = projectId;
        this.bhkType = bhkType;
        this.carpetAreaSqft = carpetAreaSqft;
        this.price = price;
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

	public String getBhkType() {
		return bhkType;
	}

	public void setBhkType(String bhkType) {
		this.bhkType = bhkType;
	}

	public Integer getCarpetAreaSqft() {
		return carpetAreaSqft;
	}

	public void setCarpetAreaSqft(Integer carpetAreaSqft) {
		this.carpetAreaSqft = carpetAreaSqft;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

    
}