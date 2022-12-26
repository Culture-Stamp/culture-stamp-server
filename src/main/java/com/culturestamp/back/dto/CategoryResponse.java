package com.culturestamp.back.dto;

import com.culturestamp.back.controller.request.CategoryRequest;
import com.culturestamp.back.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
	private String category_name;
	private String review_count;

	public CategoryResponse(Category category){
		this.category_name = category.getCategoryName();
		this.review_count = category.getReviewCount();
	}
}
