package com.culturestamp.back.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.culturestamp.back.controller.request.CategoryRequest;
import com.culturestamp.back.dto.CategoryResponse;
import com.culturestamp.back.entity.Category;
import com.culturestamp.back.repository.CategoryRepository;
import com.culturestamp.back.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

	final private CategoryRepository categoryRepository;

	@Override
	public CategoryResponse addCategory(CategoryRequest categoryRequest){
		// final UserResponse user = userService.getUserById(categoryRequest.getUserId());
		final Category category = categoryRepository.save(categoryRequest.toEntity(categoryRequest));
		return new CategoryResponse(category);

	}

	@Override
	public List<Category> findAllCategory(){
		return categoryRepository.findAll();
	}

	@Override
	public CategoryResponse findCategory(Long categoryId)  {
		final Category category =  categoryRepository.findById(categoryId).orElseThrow(NullPointerException::new);
		return new CategoryResponse(category);
	}

	@Override
	public void removeCategory(Long categoryId){
		categoryRepository.deleteById(categoryId);
	}

	@Override
	public CategoryResponse modifyCategory(Long categoryId, CategoryRequest categoryRequest) throws Exception {
		Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
		if (optionalCategory.isEmpty()) {
			throw new EntityNotFoundException(
				"Category not present in the database"
			);
		}
		Category modifyCategory = optionalCategory.get();
		if(categoryRequest.getCategoryName()!=null)modifyCategory.setCategoryName(categoryRequest.getCategoryName());
		if(categoryRequest.getReviewCount()!=null)modifyCategory.setReviewCount(categoryRequest.getReviewCount());
		categoryRepository.save(modifyCategory);
		return new CategoryResponse(modifyCategory);
	}


}
