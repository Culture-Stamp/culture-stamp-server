package com.culturestamp.back.controller.request;

import com.culturestamp.back.entity.Category;
import com.culturestamp.back.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class CategoryRequest {
	private String categoryName;
	private Long reviewCount;
	private User user;

	@Builder
	public CategoryRequest(String categoryName, Long reviewCount, User user) {
		this.categoryName = categoryName;
		this.reviewCount = reviewCount;
		this.user = user;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(Long reviewCount) {
		this.reviewCount = reviewCount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category toEntity(CategoryRequest categoryRequest) {
		return Category.builder()
			.categoryName(categoryRequest.categoryName)
			.reviewCount(categoryRequest.reviewCount)
			.user(categoryRequest.user)
			.build();
	}

}

