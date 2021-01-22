package dev.uybv.bid.dto.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import dev.uybv.bid.dao.Media;

public class BidDto {
	
	public BidDto() {}

	public BidDto(@NotBlank(message = "Name must not be blank") String name, String description,
			@NotNull(message = "Price Min must not be null") Integer priceMin, Integer priceMax,
			@NotNull(message = "Price Step must not be null") Integer priceStep,
			@NotNull(message = "Start time must not be null") Integer startTime,
			@NotNull(message = "End time must not be null") Integer endTime, MemberDto owner) {
		super();
		this.name = name;
		this.description = description;
		this.priceMin = priceMin;
		this.priceMax = priceMax;
		this.priceStep = priceStep;
		this.startTime = startTime;
		this.endTime = endTime;
		this.owner = owner;
	}

	private Integer id;
	private @NotBlank(message = "Name must not be blank") String name;
	private String description;
	private @NotNull(message = "Price Min must not be null") Integer priceMin;
	private Integer priceMax;
	private @NotNull(message = "Price Step must not be null") Integer priceStep;
	private @NotNull(message = "Start time must not be null") Integer startTime;
	private @NotNull(message = "End time must not be null") Integer endTime;
	private MemberDto owner;
	private Integer createdAt;
	private Integer lastUpdatedAt;

	private List<Media> mediaFiles;

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

	public MemberDto getOwner() {
		return owner;
	}

	public void setOwner(MemberDto member) {
		this.owner = member;
	}

	public List<Media> getMediaFiles() {
		return mediaFiles;
	}

	public void setMediaFiles(List<Media> mediaFiles) {
		this.mediaFiles = mediaFiles;
	}

	public Integer getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Integer createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(Integer lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}
}
