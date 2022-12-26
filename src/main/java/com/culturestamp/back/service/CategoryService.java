package com.culturestamp.back.service;

import java.util.List;

import com.culturestamp.back.controller.request.CategoryRequest;
import com.culturestamp.back.dto.CategoryResponse;
import com.culturestamp.back.entity.Category;

public interface CategoryService {
	CategoryResponse addCategory(CategoryRequest categoryRequest);
	List<Category> findAllCategory();
	CategoryResponse findCategory(Long categoryId);
	void removeCategory(Long categoryId);

	CategoryResponse modifyCategory(Long categoryId, CategoryRequest categoryRequest) throws Exception;
}
