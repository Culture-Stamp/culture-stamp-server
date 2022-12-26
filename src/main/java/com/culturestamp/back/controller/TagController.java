package com.culturestamp.back.controller;

import com.culturestamp.back.controller.request.TagRequest;
import com.culturestamp.back.dto.CommonResponse;
import com.culturestamp.back.dto.TagResponse;
import com.culturestamp.back.service.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/tag")
public class TagController {
	private final TagService service;

	@PostMapping()
	public ResponseEntity<?> TagAdd(@RequestBody TagRequest tagRequest){

		TagResponse tagResponse = service.addTag(tagRequest);
		return ResponseEntity.ok(new CommonResponse<>("SUCCESS",tagResponse));
	}

	@GetMapping()
	public ResponseEntity<List> todoAll() {
		return ResponseEntity.ok(service.findAllTag());
	}

	@GetMapping("/{tagId}")
	public ResponseEntity<?> tagDetails(@PathVariable(name = "tagId") Long tagId) throws Exception {
		// return ResponseEntity.ok(service.findTodo(todoId));
		TagResponse tagResponse = service.findTag(tagId);
		return ResponseEntity.ok(new CommonResponse<>("SUCCESS",tagResponse));
	}

	@DeleteMapping("/{tagId}")
	public ResponseEntity<Void> tagRemove(@PathVariable Long tagId ){
		service.removeTag(tagId);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{tagId}")
	public ResponseEntity<TagResponse> tagModify(@PathVariable Long tagId ,@RequestBody TagRequest tagRequest) throws Exception {
		return ResponseEntity.ok(service.modifyTag(tagId,tagRequest));
	}


}
