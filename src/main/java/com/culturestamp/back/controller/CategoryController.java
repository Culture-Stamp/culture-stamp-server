package com.culturestamp.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.culturestamp.back.controller.request.CategoryRequest;
import com.culturestamp.back.dto.CategoryResponse;
import com.culturestamp.back.entity.Category;
import com.culturestamp.back.service.CategoryService;
import com.culturestamp.back.service.impl.CategoryServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {

	private final CategoryService service;

	@PostMapping()
	public CategoryResponse categoryAdd(@RequestBody CategoryRequest categoryRequest){
		Category category = service.addCategory(categoryRequest);
		return new CategoryResponse(category);
	}

	@GetMapping("/{categoryId}")
	public CategoryResponse categoryDetails(@PathVariable(name = "categoryId") Long categoryId) throws Exception {
		Category category =  service.findCategory(categoryId);
		return new CategoryResponse(category);
	}

	@DeleteMapping("/{categoryId}")
	public void categoryRemove(@PathVariable Long categoryId){
		service.removeCategory(categoryId);
	}

	@PatchMapping("/{categoryId}")
	public CategoryResponse categoryModify(@PathVariable Long categoryId ,@RequestBody CategoryRequest categoryRequest) throws Exception {
		final Category category =  service.modifyCategory(categoryId,categoryRequest);
		return new CategoryResponse(category);
	}


}
