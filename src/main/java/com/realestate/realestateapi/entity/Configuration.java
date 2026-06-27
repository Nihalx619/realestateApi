package com.realestate.realestateapi.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "configurations")
public class Configuration {

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
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

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String bhkType;

    private Integer carpetAreaSqft;

    private BigDecimal price;
}
