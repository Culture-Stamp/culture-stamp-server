package com.culturestamp.back.controller;

import com.culturestamp.back.controller.request.ReviewEditorRequest;
import com.culturestamp.back.controller.request.ReviewRequest;
import com.culturestamp.back.dto.ReviewResponse;
import com.culturestamp.back.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/review")
public class ReviewController {
    private final ReviewService service;

    // 리뷰 아이디, 썸네일(대표이미지)
    @GetMapping()
    public ResponseEntity<List<ReviewResponse>> reviewList(@PageableDefault(size=20) Pageable pageable){
        return ResponseEntity.ok().body( service.findReviews(pageable) );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> reviewDetails(@PathVariable Long id){
        return ResponseEntity.ok().body( service.findReview(id) );
    }

    @PostMapping()
    public ResponseEntity<ReviewResponse> reviewAdd(@RequestBody ReviewRequest reviewRequest){
        return ResponseEntity.ok( service.addReview(reviewRequest) );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReviewResponse> reviewModify(@PathVariable Long id, @RequestBody ReviewEditorRequest reviewRequest){
        return ResponseEntity.ok( service.modifyReview(id, reviewRequest) );
    }

    @DeleteMapping("/{id}")
    public void reviewRemove(@PathVariable Long id){
        service.removeReview(id);
    }
}
