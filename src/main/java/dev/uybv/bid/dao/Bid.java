package dev.uybv.bid.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bid {
	
	public Bid() {}
	
	public Bid(String name, String description, Integer priceMin, Integer priceMax, Integer priceStep,
			Integer startTime, Integer endTime, Integer ownerId) {
		super();
		this.name = name;
		this.description = description;
		this.priceMin = priceMin;
		this.priceMax = priceMax;
		this.priceStep = priceStep;
		this.startTime = startTime;
		this.endTime = endTime;
		this.ownerId = ownerId;
	}

	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Integer id;
	private @Column(nullable = false, length = 255) String name;
	private String description;
	private @Column(nullable = false) Integer priceMin;
	private Integer priceMax;
	private Integer priceStep;
	private @Column(nullable = false) Integer startTime;
	private @Column(nullable = false) Integer endTime;
	private @Column(nullable = false) Integer ownerId;
	private @Column(updatable = false) Long createdAt;
	private Long lastUpdatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(Integer priceMin) {
		this.priceMin = priceMin;
	}

	public Integer getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(Integer priceMax) {
		this.priceMax = priceMax;
	}

	public Integer getPriceStep() {
		return priceStep;
	}

	public void setPriceStep(Integer priceStep) {
		this.priceStep = priceStep;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Long getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(Long lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

}
