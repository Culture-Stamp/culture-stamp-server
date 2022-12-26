package com.culturestamp.back.controller;

import com.culturestamp.back.controller.request.CategoryRequest;
import com.culturestamp.back.dto.CategoryResponse;
import com.culturestamp.back.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {
	//
	private final CategoryService service;

	@PostMapping()
	public ResponseEntity<CategoryResponse> categoryAdd(@RequestBody CategoryRequest categoryRequest){
		return ResponseEntity.ok( service.addCategory(categoryRequest) );
	}

	@GetMapping()
	public List<CategoryResponse> categoryAll() {
		return service.findAllCategory().stream()
			.map(CategoryResponse::fromEntity).collect(Collectors.toList());
	}
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryResponse>  categoryDetails(@PathVariable(name = "categoryId") Long categoryId) throws Exception {
		return ResponseEntity.ok().body( service.findCategory(categoryId) );
	}

	@DeleteMapping("/{categoryId}")
	public void categoryRemove(@PathVariable Long categoryId){
		service.removeCategory(categoryId);
	}

	@PatchMapping("/{categoryId}")
	public ResponseEntity<CategoryResponse>  categoryModify(@PathVariable Long categoryId ,@RequestBody CategoryRequest categoryRequest) throws Exception {
		return ResponseEntity.ok( service.modifyCategory(categoryId, categoryRequest) );
	}


}
