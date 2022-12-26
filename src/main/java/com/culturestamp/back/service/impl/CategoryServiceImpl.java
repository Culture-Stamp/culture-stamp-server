package com.culturestamp.back.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.culturestamp.back.controller.request.CategoryRequest;
import com.culturestamp.back.dto.UserResponse;
import com.culturestamp.back.entity.Category;
import com.culturestamp.back.entity.User;
import com.culturestamp.back.repository.CategoryRepository;
import com.culturestamp.back.service.CategoryService;
import com.culturestamp.back.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	final private CategoryRepository categoryRepository;
	// final private UserService userService;
	@Override
	public Category addCategory(CategoryRequest categoryRequest){
		// final UserResponse user = userService.getUserById(categoryRequest.getUserId());
		final Category category = new Category(categoryRequest.getCategoryName(), categoryRequest.getReviewCount());
		return categoryRepository.save(category);
	}
	@Override
	public Category findCategory(Long categoryId)  {
		return categoryRepository.findById(categoryId).orElseThrow(NullPointerException::new);
	}
	@Override
	public void removeCategory(Long categoryId){
		categoryRepository.deleteById(categoryId);
	}

	@Override
	public Category modifyCategory(Long categoryId, CategoryRequest categoryRequest) throws Exception {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if (optionalCategory.isEmpty()) {
			throw new EntityNotFoundException(
				"Category not present in the database"
			);
		}
		Category modifyCategory = optionalCategory.get();
		if(categoryRequest.getCategoryName()!=null)modifyCategory.setCategoryName(categoryRequest.getCategoryName());
		if(categoryRequest.getReviewCount()!=null)modifyCategory.setReviewCount(categoryRequest.getReviewCount());
		return categoryRepository.save(modifyCategory);
	}


}
