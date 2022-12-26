package com.culturestamp.back.dto;

import com.culturestamp.back.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
	private String category_name;
	private Long review_count;

	public CategoryResponse(Category category){
		this.category_name = category.getCategoryName();
		this.review_count = category.getReviewCount();
	}

	public static CategoryResponse fromEntity(Category category) {
		return CategoryResponse.builder()
			.category_name(category.getCategoryName())
			.review_count(category.getReviewCount())
			.build();
	}
}
