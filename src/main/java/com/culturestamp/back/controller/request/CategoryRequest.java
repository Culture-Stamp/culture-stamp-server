package com.culturestamp.back.controller.request;

import com.culturestamp.back.entity.User;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryRequest {
	private String categoryName;
	private String reviewCount;
	private Long userId;

	public CategoryRequest(String categoryName, String reviewCount, Long userId) {
		this.categoryName = categoryName;
		this.reviewCount = reviewCount;
		this.userId = userId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(String reviewCount) {
		this.reviewCount = reviewCount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}

